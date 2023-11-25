import React from 'react';
import './Modal.css';
interface ModalProps {
    active: boolean;
    setActive: (value: boolean) => void;
    children?: React.ReactNode;
    data: string;
}

const Modal = ({ active, setActive, children, data}: ModalProps) => {
  return (
    <div className={active ? 'modal active':"modal"}>
            <div className={active ? 'modal__content active':"modal__content"}>
              <div className='content'> 
                {children}
                {data}
                <button className='closeButton' onClick={()=>setActive(false)}>Хорошо!</button>
              </div>
            </div>
        </div>
  );
};

export default Modal;