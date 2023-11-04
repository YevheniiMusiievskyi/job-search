import {Topic} from "../models/websocket";
import {Stomp} from "@stomp/stompjs";
import SockJS from 'sockjs-client';

const connectWebSocket = (topics: Topic[]) => {
    const stompClient = Stomp.over(new SockJS('/ws'));

    stompClient.connect({}, () => {
        console.log('connected');

        topics.forEach(t => {
            stompClient.subscribe(t.destination, message => {
                const result = JSON.parse(message.body);
                t.callback(result);
            });
        });
    });

    return stompClient;
};

export default connectWebSocket;