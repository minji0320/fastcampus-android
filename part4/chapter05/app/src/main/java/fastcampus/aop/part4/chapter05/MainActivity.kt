package fastcampus.aop.part4.chapter05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fastcampus.aop.part4.chapter05.data.database.DataBaseProvider
import fastcampus.aop.part4.chapter05.data.entity.GithubOwner
import fastcampus.aop.part4.chapter05.data.entity.GithubRepoEntity
import fastcampus.aop.part4.chapter05.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val repositoryDao by lazy {
        DataBaseProvider.provideDB(applicationContext).repositoryDao()
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launch {
            addMockData()
            val githubRepositories = loadGithubRepositories()
            withContext(coroutineContext) {
                Log.e("repositories", githubRepositories.toString())
            }
        }

    }

    private suspend fun addMockData() = with(Dispatchers.IO) {
        val mockData = (0 until 10).map {
            GithubRepoEntity(
                name = "repo $it",
                fullName = "name $it",
                owner = GithubOwner(
                    "login",
                    "avatarUrl"
                ),
                description = null,
                language = null,
                updatedAt = Date().toString(),
                stargazersCount = it
            )
        }
        repositoryDao.insertAll(mockData)
    }

    private suspend fun loadGithubRepositories() = withContext(Dispatchers.IO) {
        val repositories = repositoryDao.getHistory()
        return@withContext repositories
    }
}