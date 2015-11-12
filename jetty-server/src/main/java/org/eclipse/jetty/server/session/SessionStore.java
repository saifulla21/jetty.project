//
//  ========================================================================
//  Copyright (c) 1995-2015 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//


package org.eclipse.jetty.server.session;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.util.component.LifeCycle;

/**
 * SessionStore
 *
 * A store of Session objects.  This store of Session objects can be backed by
 * a SessionDataStore to persist/distribute the data contained in the Session objects.
 * 
 * This store of Session objects ensures that all threads within the same context on
 * the same node with the same session id will share exactly the same Session object.
 */
public interface SessionStore extends LifeCycle
{
    Session newSession (HttpServletRequest request, SessionKey key,  long time, long maxInactiveMs);
    Session get(SessionKey key, boolean staleCheck) throws Exception;
    void put(SessionKey key, Session session) throws Exception;
    boolean exists (SessionKey key) throws Exception;
    boolean delete (SessionKey key) throws Exception;
    void shutdown ();
    Set<SessionKey> getExpired ();
}
