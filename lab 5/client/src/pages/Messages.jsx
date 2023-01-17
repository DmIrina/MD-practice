import "../styles/App.css"
import {useFetching} from "../hooks/useFetching";
import {getPageCount} from "../utils/pages";
import Loader from "../components/UI/Loader/Loader";
import Pagination from "../components/UI/pagination/Pagination";
import {useEffect, useState} from "react";
import {useMessages} from "../hooks/useMessages";
import MessageService from "../API/MessageService";
import MessageList from "../components/messages/MessageList";


function Messages() {
    const [messages, setMessages] = useState([])

    const [filter, setFilter] = useState({sort: '', query: ''});
    const [modal, setModal] = useState(false);
    const [totalPages, setTotalPages] = useState(0);    // кількість сторінок
    const [limit, setLimit] = useState(10); // кількість елементів на сторінку
    const [page, setPage] = useState(1);    //номер сторінки
    const [needFetch, setNeedFetch] = useState(true);    //номер сторінки

    const sortedAndSearchedMessages = useMessages(messages, filter.sort, filter.query);

    const [fetchMessages, isMessagesLoading, error] = useFetching(async () => {
        const response = await MessageService.getAll(limit, page);
        setMessages(response.data);
        const totalCount = response.headers['x-total-count'];           // кількість постів
        setTotalPages(getPageCount(totalCount, limit));
    })

    // якщо залежності - пустий масив [] - useEffect спрацює лише один раз при монтуванні компонента
    useEffect(() => {
        fetchMessages();
    }, [page, needFetch])

//     // callback - функція необхідна для отримання параметра  newSession з нижнього компонента
//     const createMessage = async (newMessage) => {
// //        setSessions([...sessions, newSession])    // !needFetch і так все перезавантажить
//         const response = await MessageService.post(newMessage);
//         setNeedFetch(!needFetch);
//         setModal(false)
//     }

    // Отримати  з дочернього компонента
    const removeMessage = async (message) => {
        const response = await MessageService.delete(message.id);
        setMessages(message.filter(p => p.id !== message.id));
    }

    const changePage = (page) => {
        setPage(page);
        //   fetchSessions();
    }

    return (
        <div className="App">
            {error && <h1>Виникла помилка ${error}</h1>}
            {isMessagesLoading
                ? <div style={{display: 'flex', justifyContent: 'center', marginTop: 50}}><Loader/></div>
                : <MessageList remove={removeMessage} messages={sortedAndSearchedMessages} title="Повідомлення:"/>
            }

            <Pagination page={page} changePage={changePage} totalPages={totalPages}/>

        </div>
    )
}

export default Messages;
