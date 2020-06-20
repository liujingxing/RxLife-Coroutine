package rxhttp.wrapper.param;

import com.example.coroutine.Url;
import java.io.IOException;
import java.lang.Class;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Map;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.concurrent.TaskRunner;
import rxhttp.HttpSender;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.cahce.CacheMode;
import rxhttp.wrapper.cahce.CacheStrategy;
import rxhttp.wrapper.cahce.DiskLruCacheFactory;
import rxhttp.wrapper.callback.Function;
import rxhttp.wrapper.callback.IConverter;
import rxhttp.wrapper.parse.Parser;

/**
 * Github
 * https://github.com/liujingxing/RxHttp
 * https://github.com/liujingxing/RxLife
 * https://github.com/liujingxing/okhttp-RxHttp/wiki/FAQ
 * https://github.com/liujingxing/okhttp-RxHttp/wiki/更新日志
 */
@SuppressWarnings("unchecked")
public class RxHttp<P extends Param, R extends RxHttp> extends BaseRxHttp {
  static {
    DiskLruCacheFactory.factory = (fileSystem, directory, appVersion, valueCount, maxSize) -> {               
        return new DiskLruCache(fileSystem, directory, appVersion, valueCount, maxSize, TaskRunner.INSTANCE); 
    };
  }

  protected P param;

  protected IConverter converter = RxHttpPlugins.getConverter();

  protected OkHttpClient okClient = HttpSender.getOkHttpClient();

  private long breakDownloadOffSize = 0L;

  protected RxHttp(P param) {
    this.param = param;
  }

  public static void setDebug(boolean debug) {
    HttpSender.setDebug(debug);
  }

  public static void init(OkHttpClient okHttpClient) {
    HttpSender.init(okHttpClient);
  }

  public static void init(OkHttpClient okHttpClient, boolean debug) {
    HttpSender.init(okHttpClient,debug);
  }

  public static boolean isInit() {
    return HttpSender.isInit();
  }

  /**
   * 设置统一数据解码/解密器，每次请求成功后会回调该接口并传入Http请求的结果
   * 通过该接口，可以统一对数据解密，并将解密后的数据返回即可
   * 若部分接口不需要回调该接口，发请求前，调用{@link #setDecoderEnabled(boolean)}方法设置false即可
   */
  public static void setResultDecoder(Function<String, String> decoder) {
    RxHttpPlugins.setResultDecoder(decoder);
  }

  /**
   * 设置默认的转换器
   */
  public static void setConverter(IConverter converter) {
    RxHttpPlugins.setConverter(converter);
  }

  /**
   * 设置统一公共参数回调接口,通过该接口,可添加公共参数/请求头，每次请求前会回调该接口
   * 若部分接口不需要添加公共参数,发请求前，调用{@link #setAssemblyEnabled(boolean)}方法设置false即可
   */
  public static void setOnParamAssembly(Function<Param, Param> onParamAssembly) {
    RxHttpPlugins.setOnParamAssembly(onParamAssembly);
  }

  @Override
  public OkHttpClient getOkHttpClient() {
    return okClient;
  }

  public P getParam() {
    return param;
  }

  public R setParam(P param) {
    this.param = param;
    return (R)this;
  }

  public static RxHttpNoBodyParam get(String url, Object... formatArgs) {
    return with(Param.get(format(url, formatArgs)));
  }

  public static RxHttpNoBodyParam head(String url, Object... formatArgs) {
    return with(Param.head(format(url, formatArgs)));
  }

  public static RxHttpFormParam postForm(String url, Object... formatArgs) {
    return with(Param.postForm(format(url, formatArgs)));
  }

  public static RxHttpFormParam putForm(String url, Object... formatArgs) {
    return with(Param.putForm(format(url, formatArgs)));
  }

  public static RxHttpFormParam patchForm(String url, Object... formatArgs) {
    return with(Param.patchForm(format(url, formatArgs)));
  }

  public static RxHttpFormParam deleteForm(String url, Object... formatArgs) {
    return with(Param.deleteForm(format(url, formatArgs)));
  }

  public static RxHttpJsonParam postJson(String url, Object... formatArgs) {
    return with(Param.postJson(format(url, formatArgs)));
  }

  public static RxHttpJsonParam putJson(String url, Object... formatArgs) {
    return with(Param.putJson(format(url, formatArgs)));
  }

  public static RxHttpJsonParam patchJson(String url, Object... formatArgs) {
    return with(Param.patchJson(format(url, formatArgs)));
  }

  public static RxHttpJsonParam deleteJson(String url, Object... formatArgs) {
    return with(Param.deleteJson(format(url, formatArgs)));
  }

  public static RxHttpJsonArrayParam postJsonArray(String url, Object... formatArgs) {
    return with(Param.postJsonArray(format(url, formatArgs)));
  }

  public static RxHttpJsonArrayParam putJsonArray(String url, Object... formatArgs) {
    return with(Param.putJsonArray(format(url, formatArgs)));
  }

  public static RxHttpJsonArrayParam patchJsonArray(String url, Object... formatArgs) {
    return with(Param.patchJsonArray(format(url, formatArgs)));
  }

  public static RxHttpJsonArrayParam deleteJsonArray(String url, Object... formatArgs) {
    return with(Param.deleteJsonArray(format(url, formatArgs)));
  }

  public static RxHttpNoBodyParam with(NoBodyParam noBodyParam) {
    return new RxHttpNoBodyParam(noBodyParam);
  }

  public static RxHttpFormParam with(FormParam formParam) {
    return new RxHttpFormParam(formParam);
  }

  public static RxHttpJsonParam with(JsonParam jsonParam) {
    return new RxHttpJsonParam(jsonParam);
  }

  public static RxHttpJsonArrayParam with(JsonArrayParam jsonArrayParam) {
    return new RxHttpJsonArrayParam(jsonArrayParam);
  }

  public R setUrl(String url) {
    param.setUrl(url);
    return (R)this;
  }

  public R addHeader(String line) {
    param.addHeader(line);
    return (R)this;
  }

  public R addHeader(String line, boolean isAdd) {
    if(isAdd) {
      param.addHeader(line);
    }
    return (R)this;
  }

  public R addHeader(String key, String value) {
    param.addHeader(key,value);
    return (R)this;
  }

  public R addHeader(String key, String value, boolean isAdd) {
    if(isAdd) {
      param.addHeader(key,value);
    }
    return (R)this;
  }

  public R addAllHeader(Map<String, String> headers) {
    param.addAllHeader(headers);
    return (R)this;
  }

  public R addAllHeader(Headers headers) {
    param.addAllHeader(headers);
    return (R)this;
  }

  public R setHeader(String key, String value) {
    param.setHeader(key,value);
    return (R)this;
  }

  public R setRangeHeader(long startIndex) {
    return setRangeHeader(startIndex, -1, false);
  }

  public R setRangeHeader(long startIndex, long endIndex) {
    return setRangeHeader(startIndex, endIndex, false);
  }

  public R setRangeHeader(long startIndex, boolean connectLastProgress) {
    return setRangeHeader(startIndex, -1, connectLastProgress);
  }

  /**
   * 设置断点下载开始/结束位置
   * @param startIndex 断点下载开始位置
   * @param endIndex 断点下载结束位置，默认为-1，即默认结束位置为文件末尾
   * @param connectLastProgress 是否衔接上次的下载进度，该参数仅在带进度断点下载时生效
   */
  public R setRangeHeader(long startIndex, long endIndex, boolean connectLastProgress) {
    param.setRangeHeader(startIndex,endIndex);
    if(connectLastProgress) breakDownloadOffSize = startIndex;
    return (R)this;
  }

  @Override
  public long getBreakDownloadOffSize() {
    return breakDownloadOffSize;
  }

  public R removeAllHeader(String key) {
    param.removeAllHeader(key);
    return (R)this;
  }

  public R setHeadersBuilder(Headers.Builder builder) {
    param.setHeadersBuilder(builder);
    return (R)this;
  }

  /**
   * 设置单个接口是否需要添加公共参数,
   * 即是否回调通过{@link #setOnParamAssembly(Function)}方法设置的接口,默认为true
   */
  public R setAssemblyEnabled(boolean enabled) {
    param.setAssemblyEnabled(enabled);
    return (R)this;
  }

  /**
   * 设置单个接口是否需要对Http返回的数据进行解码/解密,
   * 即是否回调通过{@link #setResultDecoder(Function)}方法设置的接口,默认为true
   */
  public R setDecoderEnabled(boolean enabled) {
    param.addHeader(Param.DATA_DECRYPT,String.valueOf(enabled));
    return (R)this;
  }

  public boolean isAssemblyEnabled() {
    return param.isAssemblyEnabled();
  }

  public String getUrl() {
    addDefaultDomainIfAbsent(param);
    return param.getUrl();
  }

  public String getSimpleUrl() {
    return param.getSimpleUrl();
  }

  public String getHeader(String key) {
    return param.getHeader(key);
  }

  public Headers getHeaders() {
    return param.getHeaders();
  }

  public Headers.Builder getHeadersBuilder() {
    return param.getHeadersBuilder();
  }

  public R tag(Object tag) {
    param.tag(tag);
    return (R)this;
  }

  public <T> R tag(Class<? super T> type, T tag) {
    param.tag(type,tag);
    return (R)this;
  }

  public R cacheControl(CacheControl cacheControl) {
    param.cacheControl(cacheControl);
    return (R)this;
  }

  public CacheStrategy getCacheStrategy() {
    return param.getCacheStrategy();
  }

  public R setCacheKey(String cacheKey) {
    param.setCacheKey(cacheKey);
    return (R)this;
  }

  public R setCacheValidTime(long cacheValidTime) {
    param.setCacheValidTime(cacheValidTime);
    return (R)this;
  }

  public R setCacheMode(CacheMode cacheMode) {
    param.setCacheMode(cacheMode);
    return (R)this;
  }

  public Response execute() throws IOException {
    doOnStart();
    return newCall().execute();
  }

  public <T> T execute(Parser<T> parser) throws IOException {
    return parser.onParse(execute());
  }

  public Call newCall() {
    return newCall(getOkHttpClient());
  }

  public Call newCall(OkHttpClient okHttp) {
    return HttpSender.newCall(okHttp, buildRequest());
  }

  @Override
  public final Request buildRequest() {
    doOnStart();
    return param.buildRequest();
  }

  /**
   * 请求开始前内部调用，用于添加默认域名等操作
   */
  void doOnStart() {
    setConverter(param);
    addDefaultDomainIfAbsent(param);
  }

  /**
   * 给Param设置转换器，此方法会在请求发起前，被RxHttp内部调用
   */
  private R setConverter(P param) {
    param.tag(IConverter.class,converter);
    return (R)this;
  }

  /**
   * 给Param设置默认域名(如何缺席的话)，此方法会在请求发起前，被RxHttp内部调用
   */
  private P addDefaultDomainIfAbsent(P param) {
    String newUrl = addDomainIfAbsent(param.getSimpleUrl(), Url.baseUrl);
    param.setUrl(newUrl);
    return param;
  }

  public R setDomainToUpdateIfAbsent() {
    String newUrl = addDomainIfAbsent(param.getSimpleUrl(), Url.update);
    param.setUrl(newUrl);
    return (R)this;
  }

  private static String addDomainIfAbsent(String url, String domain) {
    if (url.startsWith("http")) return url;
    if (url.startsWith("/")) {
        if (domain.endsWith("/"))
            return domain + url.substring(1);
        else
            return domain + url;
    } else if (domain.endsWith("/")) {
        return domain + url;
    } else {
        return domain + "/" + url;
    }
  }

  /**
   * 通过占位符，将参数与url拼接在一起，使用标准的Java占位符协议
   */
  private static String format(String url, Object... formatArgs) {
    return formatArgs == null || formatArgs.length == 0 ? url : String.format(url, formatArgs);
  }
}
