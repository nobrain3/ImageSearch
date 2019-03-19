package kr.co.kjworld.imagesearch;

import android.util.Log;

import org.junit.Test;

import java.util.List;

import kr.co.kjworld.imagesearch.model.KakaoImageSearchService;
import kr.co.kjworld.imagesearch.model.network.RetrofitInstance;
import kr.co.kjworld.imagesearch.model.response.ImageSearchResponseData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {



        assertEquals(4, 2 + 2);
    }
}