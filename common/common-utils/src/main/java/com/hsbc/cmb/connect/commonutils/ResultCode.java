package com.hsbc.cmb.connect.commonutils;

public enum ResultCode {

        SUCCESS(20000),
        ERROR(20001);

        private final Integer responseCode;

        ResultCode(Integer responseCode) {

                this.responseCode = responseCode;
        }

        public Integer getResponseCode() {

                return responseCode;
        }

}
