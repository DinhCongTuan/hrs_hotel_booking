
-- Insert hotels
INSERT INTO hotels (name, location, description)
VALUES
  ('Hotel A', 'Location A', 'Description for Hotel A'),
  ('Hotel B', 'Location B', 'Description for Hotel B'),
  ('Hotel C', 'Location C', 'Description for Hotel C');

-- Insert rooms for Hotel A
INSERT INTO rooms (hotel_id, room_type, capacity, price_per_night, description)
VALUES
  (1, 'Single', 1, 100.00, 'Description for Single Room in Hotel A'),
  (1, 'Double', 2, 150.00, 'Description for Double Room in Hotel A'),
  (1, 'Suite', 4, 250.00, 'Description for Suite Room in Hotel A');

-- Insert rooms for Hotel B
INSERT INTO rooms (hotel_id, room_type, capacity, price_per_night, description)
VALUES
  (2, 'Single', 1, 120.00, 'Description for Single Room in Hotel B'),
  (2, 'Double', 2, 180.00, 'Description for Double Room in Hotel B'),
  (2, 'Family', 4, 280.00, 'Description for Family Room in Hotel B');

-- Insert rooms for Hotel C
INSERT INTO rooms (hotel_id, room_type, capacity, price_per_night, description)
VALUES
  (3, 'Single', 1, 110.00, 'Description for Single Room in Hotel C'),
  (3, 'Double', 2, 160.00, 'Description for Double Room in Hotel C'),
  (3, 'Suite', 4, 270.00, 'Description for Suite Room in Hotel C');

-- Insert bookings
INSERT INTO bookings (guest_name, guest_phone, room_id, check_in_date, check_out_date, status)
VALUES
  ('John Doe', '123-456-7890', 1, '2023-12-15', '2023-12-20', 'CONFIRMED'),
  ('Jane Smith', '987-654-3210', 5, '2023-12-18', '2023-12-22', 'CONFIRMED'),
  ('Alice Johnson', '555-555-5555', 9, '2023-12-20', '2023-12-25', 'PENDING');
