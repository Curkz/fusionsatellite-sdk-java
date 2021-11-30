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

import com.squareup.moshi.Json;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;

public class CryptoReq {
    @Json(name = "Currency")
    private final String currency;
    @Json(name = "RequestedAmount")
    private final BigDecimal requestedAmount;
    @Json(name = "SenderAddress")
    private final String senderAddress;

    public String getSenderAddress() {
        return senderAddress;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public String getCurrency() {
        return currency;
    }
    
    public static class Builder {

        private String currency;
        private BigDecimal requestedAmount;
        private String senderAddress;

        public Builder() {
        }

        Builder(String currency, BigDecimal requestedAmount, String senderAddress) {
            this.currency = currency;
            this.requestedAmount = requestedAmount;
            this.senderAddress = senderAddress;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return Builder.this;
        }

        public Builder requestedAmount(BigDecimal requestedAmount) {
            this.requestedAmount = requestedAmount;
            return Builder.this;
        }

        public Builder senderAddress(String senderAddress) {
            this.senderAddress = senderAddress;
            return Builder.this;
        }

        public CryptoReq build() {
            if (this.currency == null) {
                throw new NullPointerException("The property \"currency\" is null. "
                        + "Please set the value by \"currency()\". "
                        + "The properties \"currency\", \"senderAddress\" are required.");
            }
            if (this.senderAddress == null) {
                throw new NullPointerException("The property \"currency\" is null. "
                        + "Please set the value by \"currency()\". "
                        + "The properties \"currency\", \"senderAddress\" are required.");
            }
            if (this.requestedAmount == null) {
                throw new NullPointerException("The property \"requestedAmount\" is null. "
                        + "Please set the value by \"requestedAmount()\". "
                        + "The properties \"currency\", \"requestedAmount\" are required.");
            }
            return new CryptoReq(this);
        }
    }

    private CryptoReq(Builder builder) {
        this.currency = builder.currency;
        this.requestedAmount = builder.requestedAmount;
        this.senderAddress = builder.senderAddress;
    }
}