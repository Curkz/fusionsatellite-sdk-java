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

package au.com.dmg.fusion.response.paymentresponse;

import au.com.dmg.fusion.request.paymentrequest.POIData;
import au.com.dmg.fusion.response.Response;
import au.com.dmg.fusion.response.ResponseType;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public class PaymentResponse implements ResponseType {

    @Json(name = "Response")
    private final Response response;
    @Json(name = "SaleData")
    private final PaymentResponseSaleData saleData;
    @Json(name = "POIData")
    private final POIData poiData;
    @Json(name = "PaymentResult")
    private final PaymentResult paymentResult;
    @Json(name = "AllowedProductCodes")
    private final List<String> allowedProductCodes;
    @Json(name = "PaymentReceipt")
    private final List<PaymentReceipt> paymentReceipt;

    @NotNull
    public Response getResponse() {
        return response;
    }

    @NotNull
    public PaymentResponseSaleData getSaleData() {
        return saleData;
    }

    @NotNull
    public POIData getPoiData() {
        return poiData;
    }

    @Nullable
    public PaymentResult getPaymentResult() {
        return paymentResult;
    }

    @Nullable
    public List<String> getAllowedProductCodes() {
        return allowedProductCodes;
    }

    @Nullable
    public List<PaymentReceipt> getPaymentReceipt() {
        return paymentReceipt;
    }

    public static class Builder {

        private Response response;
        private PaymentResponseSaleData saleData;
        private POIData poiData;
        private PaymentResult paymentResult;
        private List<String> allowedProductCodes;
        private List<PaymentReceipt> paymentReceipt;

        public Builder() {
        }

        Builder(Response response, PaymentResponseSaleData saleData, POIData poiData, PaymentResult paymentResult, List<String> allowedProductCodes, List<PaymentReceipt> paymentReceipt) {
            this.response = response;
            this.saleData = saleData;
            this.poiData = poiData;
            this.paymentResult = paymentResult;
            this.allowedProductCodes = allowedProductCodes;
            this.paymentReceipt = paymentReceipt;
        }

        public Builder response(Response response) {
            this.response = response;
            return Builder.this;
        }

        public Builder saleData(PaymentResponseSaleData saleData) {
            this.saleData = saleData;
            return Builder.this;
        }

        public Builder POIData(POIData poiData) {
            this.poiData = poiData;
            return Builder.this;
        }

        public Builder paymentResult(PaymentResult paymentResult) {
            this.paymentResult = paymentResult;
            return Builder.this;
        }

        public Builder allowedProductCodes(List<String> allowedProductCodes) {
            this.allowedProductCodes = allowedProductCodes;
            return Builder.this;
        }

        public Builder addAllowedProductCodes(String allowedProductCodes) {
            if(this.allowedProductCodes == null){
                this.allowedProductCodes = new LinkedList<>();
            }
            this.allowedProductCodes.add(allowedProductCodes);
            return Builder.this;
        }

        public Builder paymentReceipt(List<PaymentReceipt> paymentReceipt) {
            this.paymentReceipt = paymentReceipt;
            return Builder.this;
        }

        public Builder addPaymentReceipt(PaymentReceipt paymentReceipt){
            if (this.paymentReceipt == null){
                this.paymentReceipt = new LinkedList<>();
            }

            if(paymentReceipt != null){
                this.paymentReceipt.add(paymentReceipt);
            }
            return Builder.this;
        }

        public PaymentResponse build() {
            if (this.response == null) {
                throw new NullPointerException("The property \"response\" is null. "
                        + "Please set the value by \"response()\". "
                        + "The properties \"response\", \"saleData\" and \"poiData\" are required.");
            }
            if (this.saleData == null) {
                throw new NullPointerException("The property \"saleData\" is null. "
                        + "Please set the value by \"saleData()\". "
                        + "The properties \"response\", \"saleData\" and \"poiData\" are required.");
            }
            if (this.poiData == null) {
                throw new NullPointerException("The property \"poiData\" is null. "
                        + "Please set the value by \"poiData()\". "
                        + "The properties \"response\", \"saleData\" and \"poiData\" are required.");
            }

            return new PaymentResponse(this);
        }
    }

    private PaymentResponse(Builder builder) {
        this.response = builder.response;
        this.saleData = builder.saleData;
        this.poiData = builder.poiData;
        this.paymentResult = builder.paymentResult;
        this.allowedProductCodes = builder.allowedProductCodes;
        this.paymentReceipt = builder.paymentReceipt;
    }
}


/*
package au.com.dmg.fusionsatellite.response
class PaymentResponse
@Required Response response
@Required PaymentResponseSaleData saleData
@Required POIData poiData
PaymentResult paymentResult
List<String> allowedProductCodes
List<PaymentReceipt> paymentReceipt
* */

