package rxhttp.wrapper.param;

import java.lang.Object;
import java.lang.String;
import java.util.List;

/**
 * Github
 * https://github.com/liujingxing/RxHttp
 * https://github.com/liujingxing/RxLife
 * https://github.com/liujingxing/okhttp-RxHttp/wiki/FAQ
 * https://github.com/liujingxing/okhttp-RxHttp/wiki/更新日志
 */
public class RxHttpNoBodyParam extends RxHttp<NoBodyParam, RxHttpNoBodyParam> {
  public RxHttpNoBodyParam(NoBodyParam param) {
    super(param);
  }

  public RxHttpNoBodyParam addEncoded(String key, Object value) {
    param.addEncoded(key,value);
    return this;
  }

  public RxHttpNoBodyParam removeAllBody() {
    param.removeAllBody();
    return this;
  }

  public RxHttpNoBodyParam removeAllBody(String key) {
    param.removeAllBody(key);
    return this;
  }

  public RxHttpNoBodyParam set(String key, Object value) {
    param.set(key,value);
    return this;
  }

  public RxHttpNoBodyParam setEncoded(String key, Object value) {
    param.setEncoded(key,value);
    return this;
  }

  public Object queryValue(String key) {
    return param.queryValue(key);
  }

  public List<Object> queryValues(String key) {
    return param.queryValues(key);
  }
}
