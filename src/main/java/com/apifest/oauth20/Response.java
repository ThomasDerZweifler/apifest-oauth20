/*
* Copyright 2013-2014, ApiFest project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.apifest.oauth20;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;

/**
 * Contains all supported responses and response messages.
 *
 * @author Rossitsa Borissova
 */
public final class Response {
    public static final String CANNOT_REGISTER_APP = "{\"error\": \"cannot issue client_id and client_secret\"}";
    public static final String APPNAME_OR_SCOPE_IS_NULL = "{\"error\": \"app_name and scope parameters are mandatory\"}";
    public static final String INVALID_CLIENT_ID = "{\"error\": \"invalid client_id\"}";
    public static final String RESPONSE_TYPE_NOT_SUPPORTED = "{\"error\": \"unsupported_response_type\"}";
    public static final String INVALID_REDIRECT_URI = "{\"error\": \"invalid redirect_uri\"}";
    public static final String MANDATORY_PARAM_MISSING = "{\"error\": \"mandatory paramater %s is missing\"}";
    public static final String CANNOT_ISSUE_TOKEN = "{\"error\": \"cannot issue token\"}";
    public static final String INVALID_AUTH_CODE = "{\"error\": \"invalid auth_code\"}";
    public static final String GRANT_TYPE_NOT_SUPPORTED = "{\"error\": \"unsupported_grant_type\"}";
    public static final String INVALID_ACCESS_TOKEN = "{\"error\":\"invalid access token\"}";
    public static final String INVALID_REFRESH_TOKEN = "{\"error\":\"invalid refresh token\"}";
    public static final String INVALID_USERNAME_PASSWORD = "{\"error\": \"invalid username/password\"}";
    public static final String CANNOT_AUTHENTICATE_USER = "{\"error\": \"cannot authenticate user\"}";
    public static final String NOT_FOUND_CONTENT = "{\"error\":\"Not found\"}";

    private static final String APPLICATION_JSON = "application/json";


    public static HttpResponse createBadRequestResponse() {
        return createBadRequestResponse(null);
    }

    public static HttpResponse createBadRequestResponse(String message) {
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
        response.setHeader(HttpHeaders.Names.CONTENT_TYPE, APPLICATION_JSON);
        if(message != null) {
            ChannelBuffer buf = ChannelBuffers.copiedBuffer(message.getBytes());
            response.setContent(buf);
        }
        response.setHeader(HttpHeaders.Names.CACHE_CONTROL, HttpHeaders.Values.NO_STORE);
        response.setHeader(HttpHeaders.Names.PRAGMA, HttpHeaders.Values.NO_CACHE);
        return response;
    }

    public static HttpResponse createNotFoundResponse() {
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
        response.setHeader(HttpHeaders.Names.CONTENT_TYPE, APPLICATION_JSON);
        ChannelBuffer buf = ChannelBuffers.copiedBuffer(NOT_FOUND_CONTENT.getBytes());
        response.setContent(buf);
        response.setHeader(HttpHeaders.Names.CACHE_CONTROL, HttpHeaders.Values.NO_STORE);
        response.setHeader(HttpHeaders.Names.PRAGMA, HttpHeaders.Values.NO_CACHE);
        return response;
    }

    public static HttpResponse createOkResponse(String jsonString) {
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        ChannelBuffer buf = ChannelBuffers.copiedBuffer(jsonString.getBytes());
        response.setContent(buf);
        response.setHeader(HttpHeaders.Names.CONTENT_TYPE, APPLICATION_JSON);
        response.setHeader(HttpHeaders.Names.CACHE_CONTROL, HttpHeaders.Values.NO_STORE);
        response.setHeader(HttpHeaders.Names.PRAGMA, HttpHeaders.Values.NO_CACHE);
        return response;
    }

    public static HttpResponse createOAuthExceptionResponse(OAuthException ex) {
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, ex.getHttpStatus());
        ChannelBuffer buf = ChannelBuffers.copiedBuffer(ex.getMessage().getBytes());
        response.setContent(buf);
        response.setHeader(HttpHeaders.Names.CONTENT_TYPE, APPLICATION_JSON);
        response.setHeader(HttpHeaders.Names.CACHE_CONTROL, HttpHeaders.Values.NO_STORE);
        response.setHeader(HttpHeaders.Names.PRAGMA, HttpHeaders.Values.NO_CACHE);
        return response;
    }

    public static HttpResponse createUnauthorizedResponse() {
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED);
        ChannelBuffer buf = ChannelBuffers.copiedBuffer(Response.INVALID_ACCESS_TOKEN.getBytes());
        response.setContent(buf);
        response.setHeader(HttpHeaders.Names.CACHE_CONTROL, HttpHeaders.Values.NO_STORE);
        response.setHeader(HttpHeaders.Names.PRAGMA, HttpHeaders.Values.NO_CACHE);
        return response;
    }

}
