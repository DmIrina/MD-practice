import axios from "axios";

export default class SessionService {
    static static;

    static async getAll() {
        const response = await axios.get('/api/session');
        // const response = await axios.get('http://localhost:8080/session');
        return (response);
    }

    static async getById(id) {
        const response = await axios.get(`/api/session/${id}`);
        // const response = await axios.get(`http://localhost:8080/session/${id}`);
        return (response);
    }

    static async delete(id) {
        const response = await axios.delete(`/api/session/${id}`);
        // const response = await axios.delete(`http://localhost:8080/session/${id}`);
        return response;
    }

    static async post(newSession) {
        let id;
        let errorMessage = "";
        //const response = await axios.post('http://localhost:8080/session', newSession)
        const response = await axios.post('/api/session', newSession)
            .then(response => id = response.data.id)
            .catch(error => {
                errorMessage = `Error: ${error.message}`;
                console.error('There was an error!', error);
            });
        return response;
    }
}