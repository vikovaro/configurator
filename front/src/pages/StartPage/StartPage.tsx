import { useNavigate } from "react-router-dom";

import classes from './StartPage.module.css';

export const StartPage = () => {
    const navigate = useNavigate();

    return (
      <div className={classes.RouteButton}>
        <button className={classes.Button} onClick={() => navigate('/main')}>Main</button>
        <button className={classes.Button} onClick={() => navigate('/test')}>Test</button>
      </div>
    );
};