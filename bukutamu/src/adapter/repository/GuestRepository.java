package adapter.repository;

import domain.entity.Guest;
import domain.repository.IGuestRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementasi repository menggunakan penyimpanan in-memory berbasis {@link List}.
 * Berada di layer adapter: mengimplementasikan port dari domain sekaligus
 * menyembunyikan detail struktur data dari layer di atasnya.
 */
public class GuestRepository implements IGuestRepository {
    private final List<Guest> data = new ArrayList<>();
    private int idCounter = 0;

    @Override
    public List<Guest> getAllGuests() {
        return new ArrayList<>(data);
    }

    @Override
    public Guest addGuest(String name, String purpose) {
        Guest guest = new Guest(++idCounter, name, purpose);
        data.add(guest);
        return guest;
    }

    @Override
    public List<Guest> searchGuests(String keyword) {
        String normalizedKeyword = keyword.toLowerCase();
        return data.stream()
                .filter(guest -> guest.getName().toLowerCase().contains(normalizedKeyword))
                .toList();
    }

    @Override
    public boolean deleteGuest(int id) {
        return data.removeIf(guest -> guest.getId() == id);
    }
}
