import { useState } from 'react';
import Modal from '../../components/Modal/Modal';

import catGif from './../../assets/cat2.gif';

import classes from './MainPage.module.css';

export const MainPage = () => {
  const [modalActive, setModalActive] = useState(false);

  return (
    <div className={classes.MainPage}>
      

      <div className={classes.c1}>

      </div>

      <div className={classes.c2}>

      </div>

      <div className={classes.c3}>

      </div>
      

      
    </div>
  );
};