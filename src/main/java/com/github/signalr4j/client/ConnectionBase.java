/*
Copyright (c) Microsoft Open Technologies, Inc.
All Rights Reserved
See License.txt in the project root for license information.
*/

package com.github.signalr4j.client;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.github.signalr4j.client.http.Request;
import com.github.signalr4j.client.transport.ClientTransport;

public interface ConnectionBase {

    /**
     * Returns the URL used by the connection
     */
    String getUrl();

    /**
     * Returns the credentials used by the connection
     */
    Credentials getCredentials();

    /**
     * Sets the credentials the connection should use
     */
    void setCredentials(Credentials credentials);

    /**
     * Sets the message id the connection should use
     */
    void setMessageId(String messageId);

    /**
     * Sets whether the connection should be re-attempted
     * on error or we simply disconnect and notify user
     */
    void setReconnectOnError(boolean reconnectOnError);

    /**
     * Sets weather the connection should be disconnected
     * on error
     * @param disconnectOnError weather to disconnect on error or not
     */
    void setDisconnectOnError (boolean disconnectOnError);

    /**
     * Lets user reconnect
     */
    void reconnect();

    /**
     * Sets the groups token the connection should use
     */
    void setGroupsToken(String groupsToken);

    /**
     * Sets the handler for the "Reconnecting" event
     */
    void reconnecting(Runnable handler);

    /**
     * Sets the handler for the "Reconnected" event
     */
    void reconnected(Runnable handler);

    /**
     * Sets the handler for the "Connected" event
     */
    void connected(Runnable handler);

    /**
     * Sets the handler for the "Error" event
     */
    void error(ErrorCallback handler);

    /**
     * Sets the handler for the "StateChanged" event
     */
    void stateChanged(StateChangedCallback handler);

    /**
     * Triggers the Error event
     * 
     * @param error
     *            The error that triggered the event
     * @param mustCleanCurrentConnection
     *            True if the connection must be cleaned
     */
    void onError(Throwable error, boolean mustCleanCurrentConnection);

    /**
     * Sets the handler for the "Received" event
     */
    void received(MessageReceivedHandler handler);

    void onReceived(JsonElement message);

    /**
     * Sets the handler for the "ConnectionSlow" event
     */
    void connectionSlow(Runnable handler);

    /**
     * Sets the handler for the "Closed" event
     */
    void closed(Runnable handler);

    /**
     * Returns the connection token
     */
    String getConnectionToken();

    /**
     * Returns the connection Id
     */
    String getConnectionId();

    /**
     * Returns the query string used by the connection
     */
    String getQueryString();

    /**
     * Returns the current message Id
     */
    String getMessageId();

    /**
     * Returns the connection groups token
     */
    String getGroupsToken();

    /**
     * Returns the data used by the connection
     */
    String getConnectionData();

    /**
     * Returns the connection state
     */
    ConnectionState getState();

    /**
     * Starts the connection
     * 
     * @param transport
     *            Transport to be used by the connection
     * @return Future for the operation
     */
    SignalRFuture<Void> start(ClientTransport transport);

    /**
     * Aborts the connection and closes it
     */
    void stop();

    /**
     * Closes the connection
     */
    void disconnect();

    /**
     * Sends data using the connection
     * 
     * @param data
     *            Data to send
     * @return Future for the operation
     */
    SignalRFuture<Void> send(String data);

    /**
     * Prepares a request that is going to be sent to the server
     * 
     * @param request
     *            The request to prepare
     */
    void prepareRequest(Request request);

    /**
     * Returns the connection headers
     */
    Map<String, String> getHeaders();
    
    /**
     * Add a header
     */
    void addHeader(String headerName, String headerValue);

    /**
     * Returns the Gson instance used by the connection
     */
    Gson getGson();

    /**
     * Sets the Gson instance used by the connection
     */
    void setGson(Gson gson);

    /**
     * Returns the JsonParser used by the connection
     */
    JsonParser getJsonParser();

    /**
     * Returns the Logger used by the connection
     */
    Logger getLogger();
}