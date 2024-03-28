import baseURL from '../apiConfig.js';

// Function to fetch sprintBacklog for a given sprintID
export function fetchSprintBacklog(projectCode, sprintID, success, failure) {
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
    // Fetch the sprintBacklog from the server using the sprintID and the current date
    fetch(`${baseURL}/projects/${projectCode}/sprints/${sprintID}/sprint-backlog`, request_options)
        .then(response => response.json())
        .then(data => success(data)) // Call the success callback with the response data
        .catch(error => failure(error.message)); // Call the failure callback with the error message
}

export function fromPbtoSbService(projectCode, userStoryID, sprintID, userStory, success, failure) {
    console.log("User Story ID:", userStoryID);
    const request_options = {
        method: 'PATCH',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify(userStory),
    };
    fetch(`${baseURL}/projects/${projectCode}/product-backlog/${userStoryID}/${sprintID}`, request_options)
        .then(response => response.json())
        .then(data => {
            if (data.error) {
                failure(data.error);
            } else {
                success(data);
            }
        })
        .catch(error => failure(error.message)); // Call the failure callback with the error message
}