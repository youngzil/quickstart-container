/**
 * 项目名称：quickstart-container-jetty 
 * 文件名：AsyncEchoServlet.java
 * 版本信息：
 * 日期：2018年4月25日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.container.jetty;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * AsyncEchoServlet 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年4月25日 上午11:01:06 
 * @since 1.0
 */
public class AsyncEchoServlet implements Servlet {

    /* (non-Javadoc)
     * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("AsyncEchoServlet init");
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.Servlet#getServletConfig()
     */
    @Override
    public ServletConfig getServletConfig() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see javax.servlet.Servlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("AsyncEchoServlet service");
        
    }

    /* (non-Javadoc)
     * @see javax.servlet.Servlet#getServletInfo()
     */
    @Override
    public String getServletInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see javax.servlet.Servlet#destroy()
     */
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        
    }

}
