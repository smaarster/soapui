/*
 *  soapUI, copyright (C) 2004-2010 eviware.com 
 *
 *  soapUI is free software; you can redistribute it and/or modify it under the 
 *  terms of version 2.1 of the GNU Lesser General Public License as published by 
 *  the Free Software Foundation.
 *
 *  soapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU Lesser General Public License for more details at gnu.org.
 */

package com.eviware.soapui.impl.wsdl.submit.transports.http.support.methods;

import java.io.IOException;

import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.methods.PostMethod;

import com.eviware.soapui.impl.rest.RestRequestInterface;
import com.eviware.soapui.impl.wsdl.submit.transports.http.ExtendedHttpMethod;
import com.eviware.soapui.impl.wsdl.submit.transports.http.HttpMethodSupport;
import com.eviware.soapui.impl.wsdl.submit.transports.http.SSLInfo;

/**
 * Extended PostMethod that supports limiting of response size and detailed
 * timestamps
 * 
 * @author Ole.Matzura
 */

public final class ExtendedPostMethod extends PostMethod implements ExtendedHttpMethod
{
	private HttpMethodSupport httpMethodSupport;
	private IAfterRequestInjection afterRequestInjection;
	private boolean followRedirects;

	public ExtendedPostMethod()
	{
		httpMethodSupport = new HttpMethodSupport( this );
	}

	public ExtendedPostMethod( String url )
	{
		super( url );
		httpMethodSupport = new HttpMethodSupport( this );
	}

	public String getDumpFile()
	{
		return httpMethodSupport.getDumpFile();
	}

	public void setDumpFile( String dumpFile )
	{
		httpMethodSupport.setDumpFile( dumpFile );
	}

	@Override
	public boolean getFollowRedirects()
	{
		return followRedirects;
	}

	@Override
	public void setFollowRedirects( boolean followRedirects )
	{
		this.followRedirects = followRedirects;
	}

	public boolean hasResponse()
	{
		return httpMethodSupport.hasResponse();
	}

	protected void readResponse( HttpState arg0, HttpConnection arg1 ) throws IOException, HttpException
	{
		super.readResponse( arg0, arg1 );
		httpMethodSupport.afterReadResponse( arg0, arg1 );
	}

	@Override
	public String getResponseCharSet()
	{
		return httpMethodSupport.getResponseCharset();
	}

	public long getMaxSize()
	{
		return httpMethodSupport.getMaxSize();
	}

	public void setMaxSize( long maxSize )
	{
		httpMethodSupport.setMaxSize( maxSize );
	}

	public long getResponseReadTime()
	{
		return httpMethodSupport.getResponseReadTime();
	}

	protected void writeRequest( HttpState arg0, HttpConnection arg1 ) throws IOException, HttpException
	{
		super.writeRequest( arg0, arg1 );
		httpMethodSupport.afterWriteRequest( arg0, arg1 );
		if( afterRequestInjection != null )
			afterRequestInjection.executeAfterRequest();
	}

	public void initStartTime()
	{
		httpMethodSupport.initStartTime();
	}

	public long getTimeTaken()
	{
		return httpMethodSupport.getTimeTaken();
	}

	public long getStartTime()
	{
		return httpMethodSupport.getStartTime();
	}

	public byte[] getResponseBody() throws IOException
	{
		return httpMethodSupport.getResponseBody();
	}

	public SSLInfo getSSLInfo()
	{
		return httpMethodSupport.getSSLInfo();
	}

	public String getResponseContentType()
	{
		return httpMethodSupport.getResponseContentType();
	}

	public RestRequestInterface.RequestMethod getMethod()
	{
		return RestRequestInterface.RequestMethod.POST;
	}

	public void setAfterRequestInjection( IAfterRequestInjection injection )
	{
		afterRequestInjection = injection;
	}

	public Throwable getFailureCause()
	{
		return httpMethodSupport.getFailureCause();
	}

	public boolean isFailed()
	{
		return httpMethodSupport.isFailed();
	}

	public void setFailed( Throwable t )
	{
		httpMethodSupport.setFailed( t );
	}

	public byte[] getDecompressedResponseBody() throws IOException
	{
		return httpMethodSupport.getDecompressedResponseBody();
	}

	public void setDecompress( boolean decompress )
	{
		httpMethodSupport.setDecompress( decompress );
	}

}