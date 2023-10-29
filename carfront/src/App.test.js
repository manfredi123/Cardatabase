import { render, screen, fireEvent } from '@testing-library/react';
import App from './App';

test('open add car modal form', () => {
  render(<App />);
  fireEvent.click(screen.getByText('New Car'));
  expect(screen.getByRole('dialog')).toHaveTextContent('New Car');
}); 