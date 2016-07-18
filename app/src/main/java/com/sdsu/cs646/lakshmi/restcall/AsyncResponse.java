package com.sdsu.cs646.lakshmi.restcall;

import java.util.List;

/**
 * Created by lakshmimohandev on 7/6/16.
 */
public interface AsyncResponse {
    public void processFinish(List<Data> dataList);
}
