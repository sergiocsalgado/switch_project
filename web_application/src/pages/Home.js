import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../App.css';

function Home() {
    const navigate = useNavigate();

    const handleWelcomeClick = () => {
        navigate('/projects');
    };

    return (
        <div>
            <div className='home-Container-Home'>
                <button className='home-button' onClick={handleWelcomeClick}>
                    Try the <br /> Group 5 Web App
                </button>
            </div>
        </div>
    );
}

export default Home;

