package com.kanzhun.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author:xing.yl
 * @date: 18/9/4
 */
@Service
public class MyHandler implements WebSocketHandler {

    /*在线用户列表*/
    private static final Map<String, WebSocketSession> users = new HashMap<>();


    /*新增socket*/
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        System.out.println("成功建立连接");
        String ID = session.getUri().toString().split("ID=")[1];
        System.out.println(ID);

        if (ID != null) {
            /*id对应session*/
            users.put(ID, session);
            session.sendMessage(new TextMessage("成功建立socket连接"));
            System.out.println(ID);
            System.out.println(session);
        }

        System.out.println("当前在线人数：" + users.size());


    }

    /*处理消息*/
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

        try {
            Object payload = webSocketMessage.getPayload();
            JSONObject jo = (JSONObject) JSON.toJSON(payload);
            System.out.println(jo.get("id"));
            System.out.println(jo.get("message") + ":来自" + (String) webSocketSession.getAttributes().get("WEBSOCKET_USERID") + "的消息");
            sendMessageToUser(jo.get("id")+"",new TextMessage("服务器收到了，hello!"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送信息给指定用户
     *
     * @param clientId
     * @param message
     * @return
     */
    public boolean sendMessageToUser(String clientId, TextMessage message) {
        if (users.get(clientId) == null)
        {
            return false;
        }
        WebSocketSession session = users.get(clientId);
        System.out.println("sendMessage:" + session);
        if (!session.isOpen()){
            return false;
        }
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 广播信息
     * @param message
     * @return
     */
    public boolean sendMessageToAllUsers(TextMessage message) {
        boolean allSendSuccess = true;
        Set<String> clientIds = users.keySet();
        WebSocketSession session = null;
        for (String clientId : clientIds) {
            try {
                session = users.get(clientId);
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                allSendSuccess = false;
            }
        }

        return  allSendSuccess;
    }



    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {

        if (session.isOpen()) {
            session.close();
        }
        System.out.println("连接出错");
        users.remove(getClientId(session));

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        System.out.println("连接已关闭：" + status);
        users.remove(getClientId(session));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 获取用户标识
     * @param session
     * @return
     */
    private Integer getClientId(WebSocketSession session) {
        try {
            return (Integer) session.getAttributes().get("WEBSOCKET_USERID");
        } catch (Exception e) {
            return null;
        }
    }
}
