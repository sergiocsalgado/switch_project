import React from 'react';
import logo from '../../assets/images/logo.png'

function HeaderGlobal() {
    return (
        <header className='header-global'>
            <img src={logo} alt="Logo AGILE IT" style={{ width: '500px',display: 'block', margin: '0 auto'}} />        </header>
    );
}

export default HeaderGlobal;