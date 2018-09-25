<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript">
            var websocket;

            function init() {
                websocket = new WebSocket('ws://localhost:8080/Lab7_2/chat');

                websocket.onopen = function (event) {
                    websocketOpen(event);
                };
                websocket.onmessage = function (event) {
                    websocketMessage(event);
                };
                websocket.onerror = function (event) {
                    websocketError(event);
                };
            }

            function websocketOpen(event) {
                console.log("webSocketOpen invoked");
                if (${sessionScope.user.userType eq 1}) {
                    var msg = '{"message":"' + 'Hi! I\'m currently online!' + '"'
                            + ', "sender":"' + '${sessionScope.user.name}' + '"'
                            + ', "received":""'
                            + ', "isAdmin":"' + true + '"}';
                    websocket.send(msg);
                }
            }

            function websocketMessage(event) {
                console.log("websocketMessage invoked");
                var msg = JSON.parse(event.data); // native API
                addRow(msg);
            }

            function websocketError(event) {
                console.log("websocketError invoked");
            }

            function sendMessage() {
                var msg = '{"message":"' + document.getElementById('chatinput').value + '"'
                        + ', "sender":"' + '${sessionScope.user.name}' + '"'
                        + ', "received":""'
                        + ', "isAdmin":"' + '${sessionScope.user.userType eq 1}' + '"}';
                websocket.send(msg);
            }

            function closeConnection() {
                if (${sessionScope.user.userType eq 1}) {
                    var msg = '{"message":"' + 'Goodbye! See you soon' + '"'
                            + ', "sender":"' + '${sessionScope.user.name}' + '"'
                            + ', "received":""'
                            + ', "isAdmin":"' + true + '"}';
                    websocket.send(msg);
                }
                websocket.close();
            }

            function addRow(msg) {
                var sender = '';
                var text = '';
                // if (msg.isAdmin == 'true') {
                //     sender += '(ADMIN) ';
                // }
                sender += msg.sender + ':';
                var date = new Date(msg.received).toLocaleString('en-GB');
                text += msg.message + ' (sent ' + date + ')';
                var tbody = document.getElementById('response').getElementsByTagName("TBODY")[0];
                var row = document.createElement("TR");
                var td1 = document.createElement("TD");
                // if (msg.isAdmin == 'true') {
                //     td1.style.color = 'red';
                // }
                td1.appendChild(document.createTextNode(sender));
                var td2 = document.createElement("TD");
                td2.appendChild(document.createTextNode(text));
                row.appendChild(td1);
                row.appendChild(td2);
                tbody.appendChild(row);
            }

            window.onunload = function () {
                closeConnection();
            };

            window.addEventListener("load", init);
        </script>
    </head>
    <body>
            <table id="response">
                <tbody>

                </tbody>
            </table>
            <input type="text" id="chatinput"/>
            <input id="sendBtn" type="button" value="Send" onclick="sendMessage()"/>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
