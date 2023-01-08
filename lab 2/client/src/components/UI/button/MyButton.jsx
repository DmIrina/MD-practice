import React from 'react';
import classes from "./MyButton.module.css";

const MyButton = ({children, ...props}) => {    // з пропсів вицепили children, а всі інші пропси залишили як є
    return (
        // всі props, що передаються в MyButton, будуть попадати button - в т.ч. disabled
        <button {...props} className={classes.myBtn}>
            {children}
        </button>
    );
};

export default MyButton;