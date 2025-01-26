
document.getElementById("heroForm").addEventListener("submit", function (e) {
    const fileInput = document.getElementById('icon_skill');
    const file = fileInput.files[0];

    if (file && file.size > 2 * 1024 * 1024) { // Проверка файла на размер > 2MB
        e.preventDefault();
        alert('Размер файла не должен превышать 2MB!');
    }
});

function addAttack() {
    // Получаем контейнер для атак
    const attackContainer = document.getElementById('attacks');

    // Считаем уже существующие .attack элементы для вычисления индекса
    const attackDivs = attackContainer.querySelectorAll('.attack');
    const attackIndex = attackDivs.length; // Количество уже существующих атак

    // Создаем новый div для новой атаки
    const attackDiv = document.createElement('div');
    attackDiv.className = 'attack';

    // Заполняем новый div нужной разметкой
    attackDiv.innerHTML = `
        <input type="number" name="attacks[${attackIndex}].speed_attack" placeholder="Скорость атаки">
        <input type="number" name="attacks[${attackIndex}].damage" placeholder="Урон">
        <input type="number" name="attacks[${attackIndex}].range_damage" placeholder="Урон на расстоянии">
        <input type="number" name="attacks[${attackIndex}].speed_movement" placeholder="Скорость передвижения">
        <input type="number" name="attacks[${attackIndex}].additional_spell_damage" placeholder="Доп. урон от заклинаний">
        <input type="number" name="attacks[${attackIndex}].mana_recovery" placeholder="Восстановление маны">
        <hr>
    `;

    // Добавляем новый блок атаки в контейнер
    attackContainer.appendChild(attackDiv);
}

function addArmor() {
    // Получаем контейнер для брони
    const armorContainer = document.getElementById('armors');

    // Считаем уже существующие .armor элементы для вычисления индекса
    const armorDivs = armorContainer.querySelectorAll('.armor');
    const armorIndex = armorDivs.length; // Количество уже существующих блоков брони

    // Создаем новый div для новой брони
    const armorDiv = document.createElement('div');
    armorDiv.className = 'armor';

    // Заполняем новый div нужной разметкой
    armorDiv.innerHTML = `
        <input type="number" name="armors[${armorIndex}].armor" placeholder="Броня" step="any">
        <input type="number" name="armors[${armorIndex}].protection_physical" placeholder="Физическая защита" step="any">
        <input type="number" name="armors[${armorIndex}].protection_mag" placeholder="Магическая защита" step="any">
        <input type="number" name="armors[${armorIndex}].protection_effects" placeholder="Защита от эффектов" step="any">
        <input type="number" name="armors[${armorIndex}].protection_slowing_down" placeholder="Защита от замедления" step="any">
        <input type="number" name="armors[${armorIndex}].avoidance" placeholder="Уклонение" step="any">
        <input type="number" name="armors[${armorIndex}].restoration_health" placeholder="Восстановление здоровья" step="any">
        <hr>
    `;

    // Добавляем новый блок брони в контейнер
    armorContainer.appendChild(armorDiv);
}

function addSkill() {
    // Получаем контейнер для навыков
    const skillContainer = document.getElementById('skills');

    // Считаем уже существующие .skill элементы для вычисления индекса
    const skillDivs = skillContainer.querySelectorAll('.skill');
    const skillIndex = skillDivs.length; // Количество уже существующих блоков навыков

    // Создаем новый div для нового навыка
    const skillDiv = document.createElement('div');
    skillDiv.className = 'skill';

    // Заполняем новый div нужной разметкой
    skillDiv.innerHTML = `
       
       <input type="text" name="skills[${skillIndex}].name_skill" placeholder="Имя скила">
       <input type="text" name="skills[${skillIndex}].type_damage" placeholder="Тип урона">
       <select typeof="text" id="type_skill" name="skills[${skillIndex}].type_skill" >
               <option value="">Выберите тип способности</option>
               <option>Active</option>
               <option>Passive</option>
        </select>
        <input type="text" name="skills[${skillIndex}].description_skill" placeholder="Описание">
        <input type="text" name="skills[${skillIndex}].damage_skill" placeholder="Урон">
        <h4>Дополнительные параметры скила</h4>
        <div class="parameters">
            <div class="parameter">
                <input type="text" name="skills[${skillIndex}].parameters[0].name_parameters" placeholder="Имя параметра">
                <input type="number" name="skills[${skillIndex}].parameters[0].count" placeholder="Значение параметра">
            </div>
        </div>
        <button type="button" onclick="addParameters(this)">Добавить Параметр</button>
        <label for="icon_skill_${skillIndex}">Иконка умения:</label>
        <input type="file" id="icon_skill_${skillIndex}" name="skills[${skillIndex}].icon_skill" accept="image/skill/*">
        <hr>
    `;

    // Добавляем новый блок навыка в контейнер
    skillContainer.appendChild(skillDiv);
}

function addParameters(button) {
    // Определяем skillDiv и parametersContainer
    const skillDiv = button.closest('.skill');
    const parametersContainer = skillDiv.querySelector('.parameters');

    // Определяем количество уже существующих div с классом 'parameter'
    const parameterDivs = parametersContainer.querySelectorAll('.parameter');
    let count = parameterDivs.length; // Количество уже существующих параметров

    // Создаем новый div для нового параметра
    const newParameterDiv = document.createElement('div');
    newParameterDiv.className = 'parameter';

    // Определяем index навыка
    const skillIndex = Array.from(document.querySelectorAll('.skill')).indexOf(skillDiv);

    // Заполняем новый div нужной разметкой
    newParameterDiv.innerHTML = `
        <input type="text" name="skills[${skillIndex}].parameters[${count}].name_parameters" placeholder="Имя параметра">
        <input type="number" name="skills[${skillIndex}].parameters[${count}].count" placeholder="Значение параметра">
        <hr>
    `;

    // Добавляем новый параметр в текущий блок параметров
    parametersContainer.appendChild(newParameterDiv);
}

document.addEventListener('DOMContentLoaded', function () {
    const dropdownButton = document.querySelector('.dropdown-button');
    const dropdownMenu = document.querySelector('.dropdown-menu');
    const checkboxes = dropdownMenu.querySelectorAll('input[type="checkbox"]');

    // Создаем массив выбранных идентификаторов классов на клиенте
    const selectedClassIds = [];

    checkboxes.forEach(function (checkbox) {
        if (checkbox.checked) {
            selectedClassIds.push(parseInt(checkbox.value));
        }
    });

    // Устанавливаем выбранные стартовые классы
    checkboxes.forEach(function (checkbox) {
        if (selectedClassIds.includes(parseInt(checkbox.value))) {
            checkbox.checked = true;
        }
    });

    // Обновление текста кнопки при изменении состояния чекбоксов
    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            updateButtonText();
        });
    });

    // Открытие/закрытие меню при клике на кнопку
    dropdownButton.addEventListener('click', function (event) {
        event.preventDefault(); // предотвращаем стандартное поведение
        dropdownMenu.style.display = dropdownMenu.style.display === 'none' || !dropdownMenu.style.display ? 'block' : 'none';
        dropdownButton.classList.toggle('active');
    });

    // Закрытие меню при клике вне области кнопки и списка
    document.addEventListener('click', function (event) {
        if (!dropdownButton.contains(event.target) && !dropdownMenu.contains(event.target)) {
            dropdownButton.classList.remove('active');
            dropdownMenu.style.display = 'none';
        }
    });

    // Обновление текста кнопки на основе состояния чекбоксов
    function updateButtonText() {
        const selected = [];
        checkboxes.forEach(function (checkbox) {
            if (checkbox.checked) {
                selected.push(checkbox.parentElement.textContent.trim());
            }
        });

        // Обновляем текст кнопки с выбранными опциями
        dropdownButton.textContent = selected.length ? selected.join(', ') : 'Выберите класс';
    }

    // Инициализация текста кнопки при загрузке страницы
    updateButtonText();
});