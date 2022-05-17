package fastcampus.aop.part6.chapter01.extensions

import android.content.res.Resources

fun Float.fromDpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()