import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import { MainPage, StartPage, TestPage } from './pages';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<StartPage />} />
        <Route path="/main" element={<MainPage />} />
        <Route path="/test" element={<TestPage />} />
      </Routes>
    </Router>
  );
};

export default App;

