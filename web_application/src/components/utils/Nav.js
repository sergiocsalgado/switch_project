import React, { useState, useEffect } from 'react';
import NavItem from "./NavItem";

const menus = [
    { key: '/', label: 'Home' },
    { key: 'projects', label: 'Projects' },
    { key: 'about', label: 'About' },
]

const Nav = ({ items, dispatch }) => {
    const [isFixed, setIsFixed] = useState(false);

    useEffect(() => {
        const handleScroll = () => {
            if (window.scrollY > 100) {
                setIsFixed(true);
            } else {
                setIsFixed(false);
            }
        };

        window.addEventListener('scroll', handleScroll);

        return () => {
            window.removeEventListener('scroll', handleScroll);
        };
    }, []);

    const navClassFixed = isFixed ? 'nav-container fixed' : 'nav-container';

    return (
        <nav className={navClassFixed}>
            <ul className='nav-bar'>
                {menus.map(item => (
                    item.key && (
                        <NavItem
                            key={item.key}
                            item={item}
                        />
                    )
                ))}
            </ul>
        </nav>
    )
}

export default Nav;
