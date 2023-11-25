import { ChangeEvent, useEffect, useState } from 'react';
import './TestPage.css';
import axios from 'axios';
import React, { useRef } from 'react';
import { V } from '../../shared/validators';
import { IProcessor } from '../../shared/types/dataTypes';
import configuratorService from '../../shared/services/configurator.service';
import Modal from '../../components/Modal/Modal';
import classes from '../../pages/MainPage/MainPage.module.css';
import catGif from './../../assets/cat2.gif';
import catSleepGif from './../../assets/cat1.gif';
import { U } from '../../utils/utils';

enum EModuleTypes {
  CPU = "processors",
  motherboard = "motherboards",
  GPU = "videocards",
  powerunit = "powerunits"
};

type TSendComponentData = {
  type: string
  name: string
  rang: number
  spec: string
  price: number
};

enum ESpecPlaceholder {
  processors = "Сокет",
  motherboards = "Сокет",
  videocards = "Разъем pci",
  powerunits = "Мощность (Вт)"
}

export function TestPage() {
  const [modalActive, setModalActive] = useState(false);

  const [modalData, setModalData] = useState("");
  const [modalHeader, setModalHeader] = useState<React.ReactNode>();

  const [price, setPrice] = useState<number>(0);
  const [moduleType, setModuleType] = useState<EModuleTypes>(EModuleTypes.CPU);
  const [name, setName] = useState("");
  const [rang, setRang] = useState("");
  const [spec, setSpec] = useState("");
  const [priceComp, setPriceForComponent] = useState("");



  const priceInputHandler = (e: ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    if (V.onlyNumbers(e.target.value)) {
      setPrice(+value);
    }
  }


  const errorHandler = (error: any) => {
    
    if (error.response && typeof error.response.status !== 'undefined') {
      setModalHeader([
        <h2>Ошибка...</h2>,
        <img className={classes.CatPC} src={catSleepGif} />,
      ]);
      setModalData(error.response.data);
      setModalActive(true);
    } else {
      setModalHeader([
        <h2>Ошибка...</h2>,
        <img className={classes.CatPC} src={catSleepGif} />,
      ]);
      setModalData(error.code);
      setModalActive(true);
      return;
    }
  }

  const getConfigHandler = () => {
    configuratorService.getConfiguration(price)
      .then((data) => {

        setModalHeader([
          <h2>Ваша сборка готова!</h2>,
          <img className={classes.CatPC} src={catGif} />,
        ]);
        setModalData(U.dataTransform(data,"config"));
        setModalActive(true);

      })
      .catch((error) => errorHandler(error));
  };


  const getCpusHandler = () => {
    configuratorService.getCpus().then((data) => {
      setModalHeader([
        <h2>Список процессоров на сервере:</h2>
      ]);
      setModalData(U.dataTransform(data,"cpu"));
      setModalActive(true);
    })
    .catch((error) => errorHandler(error));
  }

  const getMotherboardsHandler = () => {
    configuratorService.getMotherboards().then((data) => {
      setModalHeader([
        <h2>Список материнских плат на сервере:</h2>
      ]);
      setModalData(U.dataTransform(data,"motherboard"));
      setModalActive(true);
    })
    .catch((error) => errorHandler(error));
  }

  const getGpusHandler = () => {
    configuratorService.getGpus().then((data) => {
      setModalHeader([
        <h2>Список видеокарт на сервере:</h2>
      ]);
      setModalData(U.dataTransform(data,"gpu"));
      setModalActive(true);
    })
    .catch((error) => errorHandler(error));
  }

  const getPowerunitsHandler = () => {
    configuratorService.getPowerunits().then((data) => {
      setModalHeader([
        <h2>Список блоков питания на сервере:</h2>
      ]);
      setModalData(U.dataTransform(data,"powerunit"));
      setModalActive(true);
    })
    .catch((error) => errorHandler(error));
  }

  const sendCompHandler = () => {
    if (!V.onlyNumbers(rang)) {
      return;
    }

    if (!V.onlyNumbers(priceComp)) {
      return;
    }

    const data: TSendComponentData = {
      type: moduleType,
      name: name,
      rang: +rang,
      spec: spec,
      price: +priceComp,
    };
    configuratorService.sendComponent(data).then().catch( (error) => errorHandler(error) );
  }

  return (
    <div className="Test">
      <h2>GET запросы:</h2>

      <h5>Получить конфигурацию по цене</h5>
      <input type="text"
        placeholder="Цена"
        value={price}
        onChange={(e) => priceInputHandler(e)}
      />

      <button type="button" onClick={getConfigHandler}>
        Получить конфигурацию
      </button>

      <h5>Получить список процессоров в базе</h5>
      <button type="button" onClick={getCpusHandler}>
        Процессоры
      </button>

      <h5>Получить список материнских плат в базе</h5>
      <button type="button" onClick={getMotherboardsHandler}>
        Материнские платы
      </button>

      <h5>Получить список видеокарт в базе</h5>
      <button type="button" onClick={getGpusHandler}>
        Видеокарты
      </button>

      <h5>Получить список блоков питания в базе</h5>
      <button type="button" onClick={getPowerunitsHandler}>
        Блоки питания
      </button>

      <h2>POST запросы:</h2>
      <h5>Создать компонент (Добавить в базу)</h5>
      <select
        value={moduleType}
        onChange={(e) => setModuleType(e.target.value as EModuleTypes)}
      >
        {Object.keys(EModuleTypes).map((moduleTypeKey) => (
          <option key={moduleTypeKey} value={EModuleTypes[moduleTypeKey as keyof typeof EModuleTypes]}>
            {moduleTypeKey}
          </option>
        ))}
      </select>

      <input type="text"
        placeholder="Название"
        onChange={(e) => setName(e.target.value)}
      />

      <input type="text"
        placeholder="Оценочная мощность компонента от 1 до 3"
        onChange={(e) => setRang(e.target.value)}
      />

      <input type="text"
        placeholder={ESpecPlaceholder[moduleType as keyof typeof ESpecPlaceholder]}
        onChange={(e) => setSpec(e.target.value)}
      />

      <input type="text"
        placeholder="Цена"
        onChange={(e) => setPriceForComponent(e.target.value)}
      />

      <button type="button" onClick={() => sendCompHandler()}>Отправить данные</button>

      {
        modalActive &&
        <Modal active={modalActive} setActive={setModalActive} data={modalData}>
          {modalHeader}
        </Modal>
      }
    </div>
  );
}
