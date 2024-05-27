import csv
import os
import shutil
import hashlib
import string
import time

def create_folder_if_not_exists(folder_path):
    if not os.path.exists(folder_path):
        os.makedirs(folder_path)

def get_valid_filename(name):
    valid_chars = "-_.() %s%s" % (string.ascii_letters, string.digits)
    return ''.join(c for c in name if c in valid_chars)

def get_index_folder_name(char):
    if char.islower():
        return char.lower()
    elif char.isupper():
        return char.upper() + "_"
    elif char.isdigit():
        return char
    else:
        return "Other"

def process_passwords(processed_passwords):
    total_time = 0.0
    start_time = time.time()
    source_folder = "Unprocessed-Passwords"
    target_folder = "Index"
    create_folder_if_not_exists(target_folder)

    index_line_counts = {}

    for root, dirs, files in os.walk(source_folder):
        for file in files:
            file_path = os.path.join(root, file)

            with open(file_path, 'r', encoding='utf-8') as input_file:
                for line in input_file:
                    password = line.strip()
                    if password and password not in processed_passwords:
                        processed_passwords.add(password)
                        first_char = password[0]
                        target_subfolder = os.path.join(target_folder, get_index_folder_name(first_char))
                        create_folder_if_not_exists(target_subfolder)

                        password_hash = hashlib.md5(password.encode()).hexdigest()
                        sha128_hash = hashlib.sha1(password.encode()).hexdigest()
                        sha256_hash = hashlib.sha256(password.encode()).hexdigest()
                        output_line = f"{password}|{password_hash}|{sha128_hash}|{sha256_hash}|{file}"

                        safe_filename = get_valid_filename(first_char)
                        global output_file_path
                        
                        if first_char in index_line_counts:
                            index_line_counts[first_char] += 1
                        else:
                            index_line_counts[first_char] = 1

                        if index_line_counts[first_char] > 10000:
                            new_output_file_path = os.path.join(target_subfolder, f"output_{safe_filename}_{index_line_counts[first_char] // 10000}.txt")
                            output_file_path = new_output_file_path

                        else:
                            output_file_path = os.path.join(target_subfolder, f"output_{safe_filename}.txt")

                        with open(output_file_path, 'a', encoding='utf-8') as output_file:
                            output_file.write(output_line + '\n')

            processed_file_path = os.path.join(target_folder, file)
            shutil.move(file_path, "Processed")

    end_time = time.time()
    search_time = end_time - start_time
    total_time += search_time
    print(f"Total index time: {total_time} seconds")

def search_password(query, isPrint):
    start_time = time.time()        
    target_folder = "Index"
    search_results = []
    password = ""
    query = query.strip()
    first_char = query[0]
    target_subfolder = os.path.join(target_folder, get_index_folder_name(first_char))

    file_path = os.path.join(target_subfolder, f"output_{get_valid_filename(first_char)}.txt")

    if os.path.exists(file_path):
        with open(file_path, 'r+', encoding='utf-8') as input_file:
            for line in input_file:
                line = line.strip()
                password = line.split("|")[0]

                if password == query:
                    search_results.append(line)
            if(isPrint):
                if(search_results):
                    for asd in search_results:
                        print(asd)
            
    else:
        with open(file_path, 'a+', encoding='utf-8') as input_file:
            password_hash = hashlib.md5(password.encode()).hexdigest()
            sha128_hash = hashlib.sha1(password.encode()).hexdigest()
            sha256_hash = hashlib.sha256(password.encode()).hexdigest()
            output_line = f"{query}|{password_hash}|{sha128_hash}|{sha256_hash}| FromUserSearch"
            input_file.write(output_line + '\n')
        if(isPrint):
            print(f"Password not found: {query} and added to index list")
    end_time = time.time()
    search_time = end_time - start_time
    if(isPrint):
        print(f"Total search time: {search_time} seconds")

def measure_search_performance():
    query_passwords = ["password1", "123456", "qwerty", "password123", "letmein", "admin", "batman", "monkey", "password", "abc123"]
    total_time = 0.0

    for password in query_passwords:
        start_time = time.time()
        search_password(password, False)
        end_time = time.time()
        search_time = end_time - start_time
        total_time += search_time

    average_time = total_time / len(query_passwords)
    print(f"Average search time: {average_time} seconds")

def process_new_files(processed_passwords_Set):
    source_folder = "Unprocessed-Passwords"
    target_folder = "Index"
    total_time = 0.0
    start_time = time.time()
    for root, dirs, files in os.walk(source_folder):
        for file in files:
            file_path = os.path.join(root, file)

            with open(file_path, 'r', encoding='utf-8') as input_file:
                for line in input_file:
                    password = line.strip()
                    if password and password not in processed_passwords_Set:
                        processed_passwords_Set.add(password)  # Add password to the set
                        search_password(password,False)

            processed_file_path = os.path.join(target_folder, file)
            shutil.move(file_path, "Processed")
    
    end_time = time.time()
    search_time = end_time - start_time
    total_time += search_time
    print(f"Total search time: {total_time} seconds for again")
    return processed_passwords_Set

if __name__ == "__main__":
    processed_passwords_set = set() 
    while True:
        print("     Ana Menü\n1-)Şifreleri İndexle\n2-)Şifre Ara\n3-)Şifre Arama Performansı Ölç\n4-)İndekslemeden Sonra Eklenen Dosyadaki Şifreleri Kaydet\n5-)Çıkış")
        operation = int(input("Lütfen yapmak istediğiniz işlemin numarasını giriniz: "))
        if(operation == 1):
            processed_passwords_set = process_passwords(processed_passwords_set)
        elif(operation == 2):
            password = input("Lütfen aramak istediğiniz şifreyi giriniz: ")
            search_password(password, True)
        elif(operation == 3):
            measure_search_performance()
        elif(operation == 4):
            processed_passwords_set = process_new_files(processed_passwords_set)
        elif(operation == 5):
            break
