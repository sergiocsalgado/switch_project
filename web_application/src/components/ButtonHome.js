import React, { useContext } from 'react';
import {useNavigate} from "react-router";

function ButtonHome() {

    const navigate = useNavigate();
    const handleOnClick = () => {
        navigate("/projects");
    };

    return (
        <button className='home-button' onClick={handleOnClick}>Project Management</button>
    )
}

export default ButtonHome;