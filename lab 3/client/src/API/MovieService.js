import axios from "axios";

export default class MovieService {
    static static;

    static async getAll() {
        const response = await axios.get('/api/movie');
        // const response = await axios.get('http://localhost:8080/api/movie');
        return (response);
    }

    static async getById(id) {
        const response = await axios.get(`/api/movie/${id}`);
        // const response = await axios.get(`http://localhost:8080/api/movie/${id}`);
        return (response);
    }

    static async delete(id) {
        const response = await axios.delete(`/api/movie/${id}`);
        // const response = await axios.delete(`http://localhost:8080/api/movie/${id}`);
        return (response);
    }

    static async post(newMovie) {
        let id;
        let errorMessage = "";
        // const response = await axios.post('http://localhost:8080/api/movie', newMovie)
        const response = await axios.post('/api/movie', newMovie)
            .then(response => id = response.data.id)
            .catch(error => {
                errorMessage = `Error: ${error.message}`;
                console.error('There was an error!', error);
            });
        return (response);
    }
}