import { useState, useEffect } from 'react';
import ReactDOM from 'react-dom';
import fetch from 'cross-fetch';

const Viewport = () => {
    const [name, setName] = useState(null);

    useEffect(() => {
        fetch('/api/session')
            .then((response) => response.json())
            .then(({ name }) => setName(name));
    }, []);

    return (
        <h1>
          { name === null ? 'Loading' : `Hello ${name}` }
        </h1>
    );
};

ReactDOM.render(
    <Viewport />,
    document.getElementById('app')
);
