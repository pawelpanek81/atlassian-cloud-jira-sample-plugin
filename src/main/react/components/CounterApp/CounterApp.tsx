import React, {useEffect, useState} from 'react';
import './CounterApp.scss'
interface CounterAppProps {

}

export function CounterApp(props: CounterAppProps) {
    const [counter, setCounter] = useState(0)
    useEffect(() => {
        const interval = setInterval(() => setCounter(a => a + 1), 1000);
        return () => clearInterval(interval)
    }, [])
    return (
        <p className="counter">Counter: {counter}</p>
    )
}
