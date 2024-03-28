import baseURL from '../apiConfig.js';

//USER STORY

export function fetchUserStory(projectCode, success, failure) {
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

    fetch(`${baseURL}/projects/${projectCode}/product-backlog`, request_options)
        .then(response => response.json())
        .then(data => success(data))
        .catch(error => failure(error.message));
}

export const createUserStory = (projectCode,newUSData,success, failure) => {
    const request_options = {
        method: `POST`,
        cache: `no-cache`,
        credentials: `same-origin`,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(newUSData),
    };
    fetch(`${baseURL}/projects/${projectCode}/product-backlog`, request_options)
        .then((response) => {
            if (!response.ok) {
                throw new Error('Failed to create userStory');
            }
            return response.json();
        })
        .then((data) => {
            success(data);
        })
        .catch((error) => {
            failure(error.message);
        })
};

export const updateProductBacklog = (projectCode, success, failure) => {
    const request_options = {
        method: `PATCH`,
        cache: `no-cache`,
        credentials: `same-origin`,
        headers: {
            "Content-Type": "application/json"
        }
    };
    fetch(`${baseURL}/projects/${projectCode}/product-backlog`, request_options)
        .then((response) => {
            if (!response.ok) {
                throw new Error('Failed to update product backlog');
            }
            return response.json();
        })
        .then((data) => {
            success(data);
        })
        .catch((error) => {
            failure(error.message);
        })
};

