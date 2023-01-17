import axios from "axios";

export default class MovieService {
    static static;

    static async getAll() {
        const response = await axios.get('/api/messages');
        // const response = await axios.get('http://localhost:8083/api/messages');
        return (response);
    }

    static async getById(id) {
        const response = await axios.get(`/api/messages/${id}`);
        // const response = await axios.get(`http://localhost:8083/api/messages/${id}`);
        return (response);
    }

    static async delete(id) {
        const response = await axios.delete(`/api/messages/${id}`);
        // const response = await axios.delete(`http://localhost:8083/api/messages/${id}`);
        return (response);
    }

    static async post(newMessage) {
        let id;
        let errorMessage = "";
        // const response = await axios.post(`http://localhost:8083/api/messages/${id}`, newMessage)
        const response = await axios.post('/api/messages', newMessage)
            .then(response => id = response.data.id)
            .catch(error => {
                errorMessage = `Error: ${error.message}`;
                console.error('There was an error!', error);
            });
        return (response);
    }
}