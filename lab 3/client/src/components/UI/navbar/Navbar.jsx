import React from 'react';
import {Link} from "react-router-dom";

const Navbar = () => {
    return (
        <div className="navbar">
            <div className="navbar__links">
                <Link to="/sessions"> Сеанси </Link>
                <Link to="/movies"> Фільми </Link>
                <Link to="/"> Про сайт </Link>
            </div>
        </div>
    );
};

export default Navbar;