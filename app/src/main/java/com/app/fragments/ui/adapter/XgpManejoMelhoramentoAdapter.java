package com.app.fragments.ui.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fragments.R;
import com.app.fragments.ui.components.XgpManejoMelhoramentoComponent;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class XgpManejoMelhoramentoAdapter extends RecyclerView.Adapter<XgpManejoMelhoramentoAdapter.ViewHolder> {

    private final Context context;
    private final List<XgpManejoMelhoramentoComponent> components;
    private ObservationValidator observationValidator;

    public XgpManejoMelhoramentoAdapter(Context context, List<XgpManejoMelhoramentoComponent> components) {
        this.context = context;
        this.components = components;
    }

    public void setObservationValidator(ObservationValidator validator) {
        this.observationValidator = validator;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_xgp_manejo_melhoramento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(components.get(position));
    }

    @Override
    public int getItemCount() {
        return components.size();
    }

    public interface ObservationValidator {
        boolean isValidObservation(String value);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView Name;
        private final TextView Abbreviation;
        private final TextInputEditText inputValue;
        private final TextInputLayout inputContainer;
        private final Context context;

        private TextWatcher currentWatcher;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            Name = itemView.findViewById(R.id.nome_caracteristica);
            Abbreviation = itemView.findViewById(R.id.sigla_caracteristica);
            inputValue = itemView.findViewById(R.id.valor);
            inputContainer = itemView.findViewById(R.id.container);
        }

        void bind(XgpManejoMelhoramentoComponent component) {
            setComponentTexts(component);
            configureInput(component);
        }

        private void setComponentTexts(XgpManejoMelhoramentoComponent component) {
            Name.setText(component.getCharacteristic());
            Abbreviation.setText(component.getSigla());
        }

        private void configureInput(XgpManejoMelhoramentoComponent component) {
            clearPreviousWatcher();
            setInputValue(component);
            setupInputByType(component);
            resetInputStyle();
        }

        private void clearPreviousWatcher() {
            if (currentWatcher != null) {
                inputValue.removeTextChangedListener(currentWatcher);
                currentWatcher = null;
            }
        }

        private void setInputValue(XgpManejoMelhoramentoComponent component) {
            String value = component.getValue();
            inputValue.setText(Optional.ofNullable(value).orElse(""));
        }

        private void setupInputByType(XgpManejoMelhoramentoComponent component) {
            if (isObservationComponent(component)) {
                configureObservationInput(component);
            } else if (isExistsParameterizationOptions(component)) {
                configureParameterizedInput(component);
            } else {
                String type = component.getType();
                char typeChar = getTypeChar(type);

                switch (typeChar) {
                    case 'D':
                        configureDoubleInput(component);
                        break;
                    case 'I':
                        configureIntegerInput(component);
                        break;
                    case 'T':
                    default:
                        configureTextInput(component);
                        break;
                }
            }
        }

        private boolean isObservationComponent(XgpManejoMelhoramentoComponent component) {
            return "S".equalsIgnoreCase(component.getIsObservation());
        }

        private boolean isExistsParameterizationOptions(XgpManejoMelhoramentoComponent component) {
            return component.getListOptions().isPresent();
        }

        private Integer[] parameterizationOptionsInteger(XgpManejoMelhoramentoComponent component) {
            return component.getListOptions()
                    .map(options -> Arrays.stream(options.split(","))
                            .map(Integer::parseInt)
                            .toArray(Integer[]::new))
                    .orElse(new Integer[0]);
        }

        private String[] parameterizationOptionsString(XgpManejoMelhoramentoComponent component) {
            return component.getListOptions()
                    .map(options -> Arrays.stream(options.split(","))
                            .toArray(String[]::new))
                    .orElse(new String[0]);
        }

        private char getTypeChar(String type) {
            return Optional.ofNullable(type)
                    .filter(s -> !s.isEmpty())
                    .map(s -> s.toUpperCase().charAt(0))
                    .orElse('T');
        }

        private void configureObservationInput(XgpManejoMelhoramentoComponent component) {
            setInputTypeAndHint(InputType.TYPE_CLASS_TEXT, "Digite a observação");
            addObservationValidationWatcher(component);
        }

        private void configureParameterizedInput(XgpManejoMelhoramentoComponent component) {
            setInputTypeAndHint(InputType.TYPE_CLASS_TEXT, "Digite valor");
            addParameterizedValidationWatcher(component);
        }

        private void configureDoubleInput(XgpManejoMelhoramentoComponent component) {
            setInputTypeAndHint(InputType.TYPE_NUMBER_FLAG_DECIMAL, "Digite valor decimal");
            addDoubleValidationWatcher(component);
        }

        private void configureIntegerInput(XgpManejoMelhoramentoComponent component) {
            setInputTypeAndHint(InputType.TYPE_CLASS_NUMBER, "Digite nota");
            addIntegerValidationWatcher(component);
        }

        private void configureTextInput(XgpManejoMelhoramentoComponent component) {
            setInputTypeAndHint(InputType.TYPE_CLASS_TEXT, "Digite valor");
            addTextWatcher(component);
        }

        private void setInputTypeAndHint(int inputType, String hint) {
            inputValue.setInputType(inputType);
            inputContainer.setHint(hint);
        }

        private void addObservationValidationWatcher(XgpManejoMelhoramentoComponent component) {
            currentWatcher = createTextWatcher(component, this::validateObservationInput);
            inputValue.addTextChangedListener(currentWatcher);
        }

        private void addParameterizedValidationWatcher(XgpManejoMelhoramentoComponent component) {
            currentWatcher = createTextWatcher(component, this::validateParameterizedInput);
            inputValue.addTextChangedListener(currentWatcher);
        }

        private void addDoubleValidationWatcher(XgpManejoMelhoramentoComponent component) {
            currentWatcher = createTextWatcher(component, this::validateDoubleInput);
            inputValue.addTextChangedListener(currentWatcher);
        }

        private void addIntegerValidationWatcher(XgpManejoMelhoramentoComponent component) {
            currentWatcher = createTextWatcher(component, this::validateIntegerInput);
            inputValue.addTextChangedListener(currentWatcher);
        }

        private void addTextWatcher(XgpManejoMelhoramentoComponent component) {
            currentWatcher = createTextWatcher(component, (comp, value) -> resetInputStyle());
            inputValue.addTextChangedListener(currentWatcher);
        }

        private TextWatcher createTextWatcher(XgpManejoMelhoramentoComponent component, ValidationCallback callback) {
            return new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String value = s.toString();
                    component.setValue(value);
                    callback.validate(component, value);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            };
        }

        private void validateObservationInput(XgpManejoMelhoramentoComponent component, String value) {
            if (isEmptyValue(value)) {
                resetInputStyle();
                return;
            }

            if (observationValidator != null) {
                if (observationValidator.isValidObservation(value)) {
                    showValid();
                } else {
                    showError("Observação inválida. Verifique se contém uma descrição válida.");
                }
            } else {
                resetInputStyle();
            }
        }

        private void validateParameterizedInput(XgpManejoMelhoramentoComponent component, String value) {
            if (isEmptyValue(value)) return;

            Set<String> validOptions = new LinkedHashSet<>();
            try {
                if (getTypeChar(component.getType()) == 'I') {
                    Integer[] numbers = parameterizationOptionsInteger(component);
                    for (Integer number : numbers) {
                        validOptions.add(String.valueOf(number));
                    }
                } else {
                    String[] values = parameterizationOptionsString(component);
                    validOptions.addAll(Arrays.asList(values));
                }
            } catch (NumberFormatException e) {
                showError("Formato inválido para opções parametrizadas.");
                return;
            } catch (Exception e) {
                showError("Erro ao carregar valores parametrizados.");
                return;
            }

            if (validOptions.contains(value)) {
                showValid();
            } else {
                String options = String.join(" ou ", validOptions);
                showError(String.format("Valor inválido. Opções permitidas: %s", options.toUpperCase()));
            }
        }

        private void validateDoubleInput(XgpManejoMelhoramentoComponent component, String value) {
            if (isEmptyValue(value)) {
                return;
            }

            try {
                double numericValue = Double.parseDouble(value);
                Double min = component.getInitialValue().map(Number::doubleValue).orElse(null);
                Double max = component.getFinalValue().map(Number::doubleValue).orElse(null);
                validateRange(numericValue, min, max);
            } catch (NumberFormatException e) {
                showError("Apenas números decimais são permitidos.");
            }
        }

        private void validateIntegerInput(XgpManejoMelhoramentoComponent component, String value) {
            if (isEmptyValue(value)) {
                return;
            }

            try {
                int numericValue = Integer.parseInt(value);
                Integer min = component.getInitialValue().map(Number::intValue).orElse(null);
                Integer max = component.getFinalValue().map(Number::intValue).orElse(null);
                validateRange(numericValue, min, max);
            } catch (NumberFormatException e) {
                showError("Apenas números inteiros são permitidos.");
            }
        }

        private boolean isEmptyValue(String value) {
            if (value.isEmpty()) {
                resetInputStyle();
                return true;
            }
            return false;
        }

        private void validateRange(double value, Double min, Double max) {
            if (min == null || max == null) {
                showValid();
                return;
            }
            if (value >= min && value <= max) {
                showValid();
            } else {
                showError(String.format("Deve estar entre %s e %s.", min, max));
            }
        }

        private void validateRange(int value, Integer min, Integer max) {
            if (min == null || max == null) {
                showValid();
                return;
            }
            if (value >= min && value <= max) {
                showValid();
            } else {
                showError(String.format("Deve estar entre %s e %s.", min, max));
            }
        }

        private void showValid() {
            setInputStyle(R.color.colorValid, null);
        }

        private void showError(String message) {
            setInputStyle(R.color.colorError, message);
        }

        private void resetInputStyle() {
            setInputStyle(R.color.colorPrimary, null);
        }

        private void setInputStyle(int colorRes, String errorMessage) {
            inputContainer.setBoxStrokeColor(ContextCompat.getColor(context, colorRes));
            inputContainer.setError(errorMessage);
        }

        @FunctionalInterface
        private interface ValidationCallback {
            void validate(XgpManejoMelhoramentoComponent component, String value);
        }
    }
}