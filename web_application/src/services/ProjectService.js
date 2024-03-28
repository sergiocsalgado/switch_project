import baseURL from '../apiConfig.js';

export function fetchProjectsSer(success, failure) {
    const request_options = {
        method: `GET`,
        mode: `cors`,
        cache: `no-cache`,
        credentials: `same-origin`,
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    };
    fetch(`${baseURL}/projects`, request_options)
        .then(res => res.json())
        .then(res => {
            success(res)
        })
        .catch(err => failure(err.message))
}

export function createProject(newProjectData,success, failure) {
    const request_options = {
        method: `POST`,
        mode: `cors`,
        cache: `no-cache`,
        credentials: `same-origin`,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(newProjectData),
    };

    fetch(`${baseURL}/projects`, request_options)
        .then((response) => {
            if (!response.ok) {
                throw new Error('Failed to create project');
            }
            return response.json();
        })
        .then((data) => {
            success(data);
        })
        .catch((error) => {
            failure(error.message);
        })
}





