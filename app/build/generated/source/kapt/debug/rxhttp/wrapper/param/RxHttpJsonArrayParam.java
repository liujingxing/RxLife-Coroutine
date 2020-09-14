package rxhttp.wrapper.param;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
public class RxHttpJsonArrayParam extends RxHttpBodyParam<JsonArrayParam, RxHttpJsonArrayParam> {
  public RxHttpJsonArrayParam(JsonArrayParam param) {
    super(param);
  }

  public RxHttpJsonArrayParam add(Object object) {
    param.add(object);
    return this;
  }

  public RxHttpJsonArrayParam addAll(List<?> list) {
    param.addAll(list);
    return this;
  }

  /**
   * 添加多个对象，将字符串转JsonElement对象,并根据不同类型,执行不同操作,可输入任意非空字符串
   */
  public RxHttpJsonArrayParam addAll(String jsonElement) {
    param.addAll(jsonElement);
    return this;
  }

  public RxHttpJsonArrayParam addAll(JsonArray jsonArray) {
    param.addAll(jsonArray);
    return this;
  }

  /**
   * 将Json对象里面的key-value逐一取出，添加到Json数组中，成为单独的对象
   */
  public RxHttpJsonArrayParam addAll(JsonObject jsonObject) {
    param.addAll(jsonObject);
    return this;
  }

  public RxHttpJsonArrayParam addJsonElement(String jsonElement) {
    param.addJsonElement(jsonElement);
    return this;
  }

  /**
   * 添加一个JsonElement对象(Json对象、json数组等)
   */
  public RxHttpJsonArrayParam addJsonElement(String key, String jsonElement) {
    param.addJsonElement(key,jsonElement);
    return this;
  }
}
