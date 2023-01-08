import React, {useState} from 'react';

const DraftCounter = () => {
    // [count, setCount] count - state, setCount - функція, у параметри якої передаємо нове значення для state
    const [count, setCount] = useState(0)

    function increment() {
        setCount(count + 1)
        // console.log(count)
    }

    function decrement() {
        setCount(count - 1)
    }

    return (
        <div>
            <h3>{count}</h3>
            <button onClick={increment}>Increment</button>
            <button onClick={decrement}>Decrement</button>
        </div>
    );
};

export default DraftCounter;