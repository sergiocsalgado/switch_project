import React from "react";
import {Link} from "react-router-dom";

const NavItem = ({item}) => {
    const {key, label} = item;
    return (
        <li className='nav-li'>
            <Link className='nav-button' to={key}>{label}</Link>
        </li>
    )
};

export default NavItem;
