import baseURL from '../apiConfig.js';

export function fetchProfilesSer(success, failure) {
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
    fetch(`${baseURL}/profiles`, request_options)
        .then(res => res.json())
        .then(res => {
            success(res)
        })
        .catch(err => failure(err.message))
}

export function createProfile(newProfileData, success, failure) {
    console.log("createProfile function called");
    const request_options = {
        method: `POST`,
        mode: `cors`,
        cache: `no-cache`,
        credentials: `same-origin`,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(newProfileData),
    };

    fetch(`${baseURL}/profiles`, request_options)
        .then((response) => {
            if (!response.ok) {
                throw new Error('Failed to create profile');
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