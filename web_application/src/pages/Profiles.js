import React, {useContext, useState} from 'react';

import TableProfile from '../components/profiles/TableProfile';
import {createProfileAction} from "../context/Actions";
import AppContext from "../context/AppContext";

function Profiles() {
    const {dispatch} = useContext(AppContext);
    const [newProfile, setNewProfile] = useState({profileID: "", description: ""});

    const handleProfileSubmit = (e) => {
        e.preventDefault();
        createProfileAction(dispatch,newProfile);
        setNewProfile({
            profileID: "", description: ""
        });
    };


    return (
        <div>
            <form onSubmit={handleProfileSubmit}>
                <input
                    type="text"
                    value={newProfile.profileID}
                    placeholder="Profile ID"
                    onChange={event => setNewProfile({...newProfile, profileID: event.target.value})}
                />
                <input
                    type="text"
                    value={newProfile.description}
                    placeholder="Description"
                    onChange={event => setNewProfile({...newProfile, description: event.target.value})}
                />
                <button type="submit">Add Profile</button>
            </form>
            <div>
                <TableProfile/>
            </div>
        </div>
    );
}

export default Profiles;