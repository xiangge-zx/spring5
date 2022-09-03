package com.xuan.spring.A23;

import org.springframework.mock.web.MockHttpServletRequest;

public class TestServletDataBinderFactory {
    public static void main(String[] args) {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("birthday","1999|01|02");
        request.setParameter("address.name","西安");
    }
}
