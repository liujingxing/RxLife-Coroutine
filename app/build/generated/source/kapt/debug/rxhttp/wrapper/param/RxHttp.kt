package rxhttp.wrapper.param

import kotlin.Unit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import rxhttp.IRxHttp
import rxhttp.wrapper.callback.ProgressCallback
import rxhttp.wrapper.entity.Progress
import rxhttp.wrapper.parse.SimpleParser

inline fun <reified T> IRxHttp.executeList() = executeClass<List<T>>()

inline fun <reified T> IRxHttp.executeClass() = object : SimpleParser<T>() {}.onParse(execute())

inline fun <reified T> BaseRxHttp.asList() = asClass<List<T>>()

inline fun <reified K, reified V> BaseRxHttp.asMap() = asClass<Map<K,V>>()

inline fun <reified T> BaseRxHttp.asClass() = asParser(object : SimpleParser<T>() {})

/**
 * 调用此方法监听上传进度                                                    
 * @param coroutine  CoroutineScope对象，用于开启协程回调进度，进度回调所在线程取决于协程所在线程
 * @param progress 进度回调  
 * 注意：此方法仅在协程环境下才生效                                         
 */
fun RxHttpFormParam.upload(coroutine: CoroutineScope, progress: suspend (Progress) -> Unit):
    RxHttpFormParam {
  param.setProgressCallback(ProgressCallback { currentProgress, currentSize, totalSize ->
      val p = Progress(currentProgress, currentSize, totalSize)
      coroutine.launch { progress(p) }
  })
  return this
}
