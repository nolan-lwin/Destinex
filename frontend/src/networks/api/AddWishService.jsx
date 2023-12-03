import axios from 'axios';


export const addJob = async (jobData) => {
    const API_URL = 'http://localhost:8080/api/jobs';
    const token = localStorage.getItem('jwtToken');

    try {
        return await axios.post(`${API_URL}`, jobData, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    } catch (error) {
        console.log("test")
        console.error('Axios Error:', error);
        throw error; // Rethrow the error to propagate it further
    }
};

// ... rest of your code
