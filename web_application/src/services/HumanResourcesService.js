import baseURL from '../apiConfig.js';

// Function to fetch resources for a given project
export function fetchResources(projectCode, success, failure) {
    const request_options = {
        method: 'GET',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    };
    // Fetch the resources from the server using the project code and the current date
    fetch(`${baseURL}/projects/${projectCode}/resources`, request_options)
        .then(response => response.json())
        .then(data => success(data)) // Call the success callback with the response data
        .catch(error => failure(error.message)); // Call the failure callback with the error message
}
// Action for creating resources
export const createResources = (projectCode, newResourcesData, success, failure) => {
    const request_options = {
        method: 'POST',
        cache: `no-cache`,
        credentials: `same-origin`,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newResourcesData)
    };

    fetch(`${baseURL}/projects/${projectCode}/resources`, request_options)
        .then((response) => {
            if (!response.ok) {
                throw new Error('Resource registration failed');
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
