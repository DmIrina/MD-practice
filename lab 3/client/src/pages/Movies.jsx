import "../styles/App.css"
import {useFetching} from "../hooks/useFetching";
import {getPageCount} from "../utils/pages";
import Loader from "../components/UI/Loader/Loader";
import Pagination from "../components/UI/pagination/Pagination";
import {useEffect, useState} from "react";
import {useMovies} from "../hooks/useMovies";
import MovieService from "../API/MovieService";
import MyButton from "../components/UI/button/MyButton";
import MyModal from "../components/UI/modal/MyModal";
import MovieForm from "../components/movies/MovieForm";
import MovieList from "../components/movies/MovieList";


function Movies() {
    const [movies, setMovies] = useState([])

    const [filter, setFilter] = useState({sort: '', query: ''});
    const [modal, setModal] = useState(false);
    const [totalPages, setTotalPages] = useState(0);    // кількість сторінок
    const [limit, setLimit] = useState(10); // кількість елементів на сторінку
    const [page, setPage] = useState(1);    //номер сторінки
    const [needFetch, setNeedFetch] = useState(true);    //номер сторінки

    const sortedAndSearchedMovies = useMovies(movies, filter.sort, filter.query);

    const [fetchMovies, isMoviesLoading, error] = useFetching(async () => {
        const response = await MovieService.getAll(limit, page);
        setMovies(response.data);
        const totalCount = response.headers['x-total-count'];           // кількість постів
        setTotalPages(getPageCount(totalCount, limit));
    })

    // якщо залежності - пустий масив [] - useEffect спрацює лише один раз при монтуванні компонента
    useEffect(() => {
        fetchMovies();
    }, [page, needFetch])

    // callback - функція необхідна для отримання параметра  newSession з нижнього компонента
    const createMovie = async (newMovie) => {
//        setSessions([...sessions, newSession])    // !needFetch і так все перезавантажить
        const response = await MovieService.post(newMovie);
        setNeedFetch(!needFetch);
        setModal(false)
    }

    // Отримати  з дочернього компонента
    const removeMovie = async (movie) => {
        const response = await MovieService.delete(movie.id);
        setMovies(movie.filter(p => p.id !== movie.id));
    }

    const changePage = (page) => {
        setPage(page);
        //   fetchSessions();
    }

    return (
        <div className="App">
            <MyButton style={{margin: '10px 0 10px 0'}} onClick={() => setModal(true)}>Новий фільм</MyButton>
            <MyModal visible={modal} setVisible={setModal}>
                <MovieForm create={createMovie}/>
            </MyModal>

            {error && <h1>Виникла помилка ${error}</h1>}
            {isMoviesLoading
                ? <div style={{display: 'flex', justifyContent: 'center', marginTop: 50}}><Loader/></div>
                : <MovieList remove={removeMovie} movies={sortedAndSearchedMovies} title="Фільми:"/>
            }

            <Pagination page={page} changePage={changePage} totalPages={totalPages}/>

        </div>
    )
}

export default Movies;
