import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App.jsx';

let testBookings = [
    {
        "id": 1
    },{
        "id": 2
    }
]

ReactDOM.render(<App bookings={testBookings} />, document.getElementById('root'));