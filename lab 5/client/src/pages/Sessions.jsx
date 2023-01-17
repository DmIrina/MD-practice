import "../styles/App.css"
import {useFetching} from "../hooks/useFetching";
import {getPageCount} from "../utils/pages";
import SessionList from "../components/sessions/SessionList";
import Loader from "../components/UI/Loader/Loader";
import Pagination from "../components/UI/pagination/Pagination";
import {useEffect, useState} from "react";
import {useSessions} from "../hooks/useSessions";
import SessionService from "../API/SessionService";
import MyButton from "../components/UI/button/MyButton";
import MyModal from "../components/UI/modal/MyModal";
import SessionForm from "../components/sessions/SessionForm";

function Sessions() {
    const [sessions, setSessions] = useState([])

    const [filter, setFilter] = useState({sort: '', query: ''});
    const [modal, setModal] = useState(false);
    const [totalPages, setTotalPages] = useState(0);    // кількість сторінок
    const [limit, setLimit] = useState(10); // кількість елементів на сторінку
    const [page, setPage] = useState(1);    //номер сторінки
    const [needFetch, setNeedFetch] = useState(true);    //номер сторінки

    const sortedAndSearchedSessions = useSessions(sessions, filter.sort, filter.query);

    const [fetchSessions, isSessionsLoading, error] = useFetching(async () => {
        const response = await SessionService.getAll(limit, page);
        setSessions(response.data);
        const totalCount = response.headers['x-total-count'];           // кількість постів
        setTotalPages(getPageCount(totalCount, limit));
    })

    // якщо залежності - пустий масив [] - useEffect спрацює лише один раз при монтуванні компонента
    useEffect(() => {
        fetchSessions();
    }, [page, needFetch])

    // callback - функція необхідна для отримання параметра  newSession з нижнього компонента
    const createSession = async (newSession) => {
//        setSessions([...sessions, newSession])    // !needFetch і так все перезавантажить
        const response = await SessionService.post(newSession);
        setNeedFetch(!needFetch);
        setModal(false)
    }

    // Отримати  з дочернього компонента
    const removeSession = async (session) => {
        const response = await SessionService.delete(session.id);
        setSessions(sessions.filter(p => p.id !== session.id))
    }

    const changePage = (page) => {
        setPage(page);
        //   fetchSessions();
    }

    return (
        <div className="App">
            <MyButton style={{margin: '10px 0 10px 0'}} onClick={() => setModal(true)}>Новий сеанс</MyButton>
            <MyModal visible={modal} setVisible={setModal}>
                <SessionForm create={createSession}/>
            </MyModal>

            {error && <h1>Виникла помилка ${error}</h1>}
            {isSessionsLoading
                ? <div style={{display: 'flex', justifyContent: 'center', marginTop: 50}}><Loader/></div>
                : <SessionList remove={removeSession} sessions={sortedAndSearchedSessions} title="Сеанси:"/>
            }

            <Pagination page={page} changePage={changePage} totalPages={totalPages}/>

        </div>
    )
}

export default Sessions;
