package fastcampus.aop.part5.chapter04.home

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import fastcampus.aop.part5.chapter04.DBKey.Companion.DB_ARTICLES
import fastcampus.aop.part5.chapter04.databinding.ActivityAddArticleBinding
import fastcampus.aop.part5.chapter04.gallery.GalleryActivity
import fastcampus.aop.part5.chapter04.photo.CameraActivity
import fastcampus.aop.part5.chapter04.photo.PhotoListAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class AddArticleActivity : AppCompatActivity() {

    companion object {
        const val PERMISSION_REQUEST_CODE = 1000
        const val GALLERY_REQUEST_CODE = 1001
        const val CAMERA_REQUEST_CODE = 1002

        private const val URI_LIST_KEY = "uriList"
    }

    private lateinit var binding: ActivityAddArticleBinding
    private var imageUriList: ArrayList<Uri> = arrayListOf()

    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    private val storage: FirebaseStorage by lazy {
        Firebase.storage
    }

    private val articleDB: DatabaseReference by lazy {
        Firebase.database.reference.child(DB_ARTICLES)
    }

    private val photoListAdapter = PhotoListAdapter { uri -> removePhoto(uri) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() = with(binding) {
        photoRecyclerView.adapter = photoListAdapter

        imageAddButton.setOnClickListener {
            showPictureUploadDialog()
        }

        submitButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()
            val userId = auth.currentUser?.uid.orEmpty()

            showProgress()

            if (imageUriList.isNotEmpty()) {
                lifecycleScope.launch {
                    val results = uploadPhoto(imageUriList)
                    afterUploadPhoto(results, title, content, userId)
                }
            } else {
                uploadArticle(userId, title, content, listOf())
            }
        }
    }

    private suspend fun uploadPhoto(uriList: List<Uri>) = withContext(Dispatchers.IO) {
        val uploadDeferred: List<Deferred<Any>> = uriList.mapIndexed { index, uri ->
            lifecycleScope.async {
                try {
                    val fileName = "image_${index}.png"
                    return@async storage
                        .reference
                        .child("article/photo")
                        .child(fileName)
                        .putFile(uri)
                        .await()
                        .storage
                        .downloadUrl
                        .await()
                        .toString()
                } catch (e: Exception) {
                    e.printStackTrace()
                    return@async Pair(uri, e)
                }
            }
        }
        return@withContext uploadDeferred.awaitAll()
    }

    private fun afterUploadPhoto(
        results: List<Any>,
        title: String,
        content: String,
        userId: String,
    ) {
        val errorResults = results.filterIsInstance<Pair<Uri, Exception>>()
        val successResults = results.filterIsInstance<String>()

        when {
            errorResults.isNotEmpty() && successResults.isNotEmpty() -> {
                photoUploadErrorButContinueDialog(
                    errorResults,
                    successResults,
                    title,
                    content,
                    userId
                )
            }
            errorResults.isEmpty() && successResults.isEmpty() -> {
                uploadError()
            }
            else -> {
                uploadArticle(userId, title, content, results.filterIsInstance<String>())
            }
        }
    }

    private fun uploadArticle(
        userId: String,
        title: String,
        content: String,
        imageUrlList: List<String>,
    ) {
        val model = ArticleModel(userId, title, System.currentTimeMillis(), content, imageUrlList)
        articleDB.push().setValue(model)

        hideProgress()
        finish()
    }

    private fun showProgress() {
        binding.progressBar.isVisible = true
    }

    private fun hideProgress() {
        binding.progressBar.isVisible = false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startGalleryScreen()
            } else {
                Toast.makeText(this, "????????? ?????????????????????.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            return
        }

        when (requestCode) {
            GALLERY_REQUEST_CODE -> {
                data?.let {
                    val uriList = it.getParcelableArrayListExtra<Uri>(URI_LIST_KEY)
                    uriList?.let { list ->
                        imageUriList.addAll(list)
                        photoListAdapter.setPhotoList(imageUriList)
                    } ?: kotlin.run {
                        Toast.makeText(this, "????????? ???????????? ???????????????.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            CAMERA_REQUEST_CODE -> {
                data?.let {
                    val uriList = it.getParcelableArrayListExtra<Uri>(URI_LIST_KEY)
                    uriList?.let { list ->
                        imageUriList.addAll(list)
                        photoListAdapter.setPhotoList(imageUriList)
                    } ?: kotlin.run {
                        Toast.makeText(this, "????????? ???????????? ???????????????.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else -> {
                Toast.makeText(this, "????????? ???????????? ???????????????.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showPermissionContextPopup() {
        AlertDialog.Builder(this)
            .setTitle("????????? ???????????????.")
            .setMessage("????????? ???????????? ?????? ???????????????.")
            .setPositiveButton("??????") { _, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE)
            }
            .create()
            .show()
    }

    private fun showPictureUploadDialog() {
        AlertDialog.Builder(this)
            .setTitle("?????? ??????")
            .setMessage("?????? ?????? ????????? ???????????????")
            .setPositiveButton("?????????") { _, _ ->
                checkExternalStoragePermission {
                    startCameraScreen()
                }
            }
            .setNegativeButton("?????????") { _, _ ->
                checkExternalStoragePermission {
                    startGalleryScreen()
                }
            }
            .create()
            .show()
    }

    private fun checkExternalStoragePermission(uploadAction: () -> Unit) {
        when {
            ContextCompat.checkSelfPermission(
                this@AddArticleActivity,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                uploadAction()
            }
            shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                showPermissionContextPopup()
            }
            else -> {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE)
            }
        }
    }

    private fun startCameraScreen() {
        startActivityForResult(
            CameraActivity.newIntent(this),
            CAMERA_REQUEST_CODE
        )
    }

    private fun startGalleryScreen() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = "image/*"
//        startActivityForResult(intent, GALLERY_REQUEST_CODE)
        startActivityForResult(GalleryActivity.newIntent(this), GALLERY_REQUEST_CODE)
    }

    private fun removePhoto(uri: Uri) {
        imageUriList.remove(uri)
        photoListAdapter.setPhotoList(imageUriList)
    }

    private fun photoUploadErrorButContinueDialog(
        errorResults: List<Pair<Uri, Exception>>,
        successResults: List<String>,
        title: String,
        content: String,
        userId: String,
    ) {
        AlertDialog.Builder(this)
            .setTitle("?????? ????????? ????????? ??????")
            .setMessage("???????????? ????????? ???????????? ????????????." + errorResults.map { (uri, _) ->
                "$uri\n"
            } + "???????????? ???????????? ????????? ???????????????????")
            .setPositiveButton("?????????") { _, _ ->
                uploadArticle(userId, title, content, successResults)
            }
            .create()
            .show()
    }

    private fun uploadError() {
        Toast.makeText(this, "?????? ???????????? ??????????????????.", Toast.LENGTH_SHORT).show()
        hideProgress()
    }
}
