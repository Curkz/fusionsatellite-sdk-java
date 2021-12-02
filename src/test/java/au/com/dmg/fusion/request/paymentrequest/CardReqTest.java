/*
 * Copyright (c) 2021. DatameshGroup
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package au.com.dmg.fusion.request.paymentrequest;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.IOException;
import java.math.BigDecimal;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import au.com.dmg.fusion.util.InstantAdapter;
import au.com.dmg.fusion.util.BigDecimalAdapter;

public class CardReqTest {

    @Test
    public void testJson() {
        CardReq cardRequest = new CardReq.Builder()
                .currency("USD")
                .requestedAmount(new BigDecimal("1.0"))
                .build();

        assert (cardRequest.getCurrency().equals("USD"));
        assert (cardRequest.getRequestedAmount().equals(new BigDecimal("1.0")));

        Moshi moshi = new Moshi.Builder()
            .add(new BigDecimalAdapter())
            .add(new InstantAdapter())
            .build();
        
        JsonAdapter<CardReq> jsonAdapter = moshi.adapter(CardReq.class);
        String json = jsonAdapter.toJson(cardRequest);

        CardReq serializedCardRequest = null;
        try {
            serializedCardRequest = jsonAdapter.fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert (serializedCardRequest.getCurrency().equals("USD"));
        assert (serializedCardRequest.getRequestedAmount().equals(new BigDecimal("1.0")));
    }

    @Test
    public void testNull() {
        try {
            CardReq cardRequest = new CardReq.Builder()
                .build();
        } catch (Exception e) {
            NullPointerException nullPointerException = new NullPointerException();
            assertThat(e, instanceOf(nullPointerException.getClass()));
        }
    }

    @Test
    public void testNullCurrency() {
        try {
            CardReq cardRequest = new CardReq.Builder()
                .requestedAmount(new BigDecimal("1.0"))
                .build();
        } catch (Exception e) {
            NullPointerException nullPointerException = new NullPointerException();
            assertThat(e, instanceOf(nullPointerException.getClass()));
        }
    }

    @Test
    public void testNullRequestedAmount() {
        try {
            CardReq cardRequest = new CardReq.Builder()
                .currency("AUD")
                .build();
        } catch (Exception e) {
            NullPointerException nullPointerException = new NullPointerException();
            assertThat(e, instanceOf(nullPointerException.getClass()));
        }
    }

}
